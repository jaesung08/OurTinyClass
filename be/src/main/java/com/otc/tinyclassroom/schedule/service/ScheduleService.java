package com.otc.tinyclassroom.schedule.service;

import com.otc.tinyclassroom.global.security.jwt.JwtProvider;
import com.otc.tinyclassroom.lecture.entity.Lecture;
import com.otc.tinyclassroom.lecture.entity.type.LectureApprovalStatusType;
import com.otc.tinyclassroom.lecture.entity.type.LectureType;
import com.otc.tinyclassroom.lecture.repository.LectureRepository;
import com.otc.tinyclassroom.member.entity.Member;
import com.otc.tinyclassroom.member.entity.Role;
import com.otc.tinyclassroom.member.repository.MemberRepository;
import com.otc.tinyclassroom.schedule.dto.ScheduleCheckDto;
import com.otc.tinyclassroom.schedule.dto.ScheduleListDto;
import com.otc.tinyclassroom.schedule.dto.request.ScheduleInsertRequestDto;
import com.otc.tinyclassroom.schedule.dto.response.ScheduleListResponseDto;
import com.otc.tinyclassroom.schedule.entity.Schedule;
import com.otc.tinyclassroom.schedule.exception.ScheduleErrorCode;
import com.otc.tinyclassroom.schedule.exception.ScheduleException;
import com.otc.tinyclassroom.schedule.repository.ScheduleRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 스케줄 로직 서비스.
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleService {

    private final LectureRepository lectureRepository;

    private final MemberRepository memberRepository;

    private final ScheduleRepository scheduleRepository;
    private final JwtProvider jwtProvider;

    /**
     * 스케줄 조회 메서드.
     *
     * @param memberId     스케줄을 조회할 멤버의 pk.
     * @param scheduleDate 스케줄을 조회하려 하는 주(week)의 월요일 날짜.
     * @return 스케줄 리스트 반환.
     */
    @Transactional(readOnly = true)
    public ScheduleListResponseDto getScheduleList(String memberId, LocalDate scheduleDate) {

        // 리스트를 가져오려는 멤버가 존재하지 않을 경우 Exception 발생.
        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(() -> new ScheduleException(ScheduleErrorCode.NOT_EXIST_MEMBER));

        // 그 멤버가 배정된 반에 할당된 선생이 없을 경우 Exception 발생.
        Member teacher = memberRepository.findByRoleAndClassRoom(Role.ROLE_TEACHER, member.getClassRoom())
                .orElseThrow(() -> new ScheduleException(ScheduleErrorCode.NOT_EXIST_TEACHER));

        List<ScheduleListDto> scheduleList = scheduleRepository.findScheduleListByMemberId(memberId, teacher.getMemberId(), scheduleDate);
        return ScheduleListResponseDto.of(scheduleDate, scheduleList);
    }

    /**
     * 스케줄 삽입 메서드.
     *
     * @param requestDto 추가될 스케줄의 정보. 강의 pk이 저장되어 있음.
     */
    public void insertSchedule(ScheduleInsertRequestDto requestDto) {

        // 추가하려는 lecture가 존재하지 않은 경우 Exception 발생.
        Lecture lecture = lectureRepository.findById(requestDto.lectureId())
                .orElseThrow(() -> new ScheduleException(ScheduleErrorCode.NOT_EXIST_LECTURE));

        // 추가하려는 lecture가 승인되지 않은 경우 Exception 발생.
        if (!lecture.getApproved().equals(LectureApprovalStatusType.APPROVED)) {
            throw new ScheduleException(ScheduleErrorCode.NOT_ACCEPT_LECTURE);
        }

        // 추가하려는 강의가 특강인 경우 request의 날짜와 교시가 특강의 것과 정확히 일치해야 한다.
        if (lecture.getLectureType().equals(LectureType.SPECIAL_LECTURE)) {
            if (!lecture.getDate().equals(requestDto.scheduleDate()) || !(lecture.getTimeTable() == requestDto.timeTable())) {
                throw new ScheduleException(ScheduleErrorCode.NOT_ACCURATE_LECTURE);
            }
        }

        // 현재 사용자가 존재하지 않는 사용자인 경우 exception 발생.
        String currentUserId = jwtProvider.getCurrentUserId();
        Member currentMember = memberRepository.findById(Long.valueOf(currentUserId))
                .orElseThrow(() -> new ScheduleException(ScheduleErrorCode.NOT_EXIST_MEMBER)
                );

        // 현재 사용자가 추가하려는 강의가 정규 강의일 경우 Exception 발생.
        if (lecture.getLectureType().equals(LectureType.REGULAR_LECTURE) && currentMember.getRole().equals(Role.ROLE_STUDENT)) {
            throw new ScheduleException(ScheduleErrorCode.NO_AUTH_SCHEDULE_INSERT);
        }

        // 그 멤버가 배정된 반에 할당된 선생이 없을 경우 Exception 발생.
        Member teacher = memberRepository.findByRoleAndClassRoom(Role.ROLE_TEACHER, currentMember.getClassRoom())
                .orElseThrow(() -> new ScheduleException(ScheduleErrorCode.NOT_EXIST_TEACHER));

        // 사용자 role에 따라 삭제 가능여부를 판단.
        boolean deletable = currentMember.getRole() == Role.ROLE_STUDENT;

        // 시간표에 있는지 확인하기.
        scheduleRepository.findScheduleByMemberIdAndScheduleDateAndTimeTable(
                currentMember.getMemberId(),
                teacher.getMemberId(),
                requestDto.scheduleDate(),
                requestDto.timeTable()
        ).ifPresent(e -> {
            throw new ScheduleException(ScheduleErrorCode.ALREADY_EXISTED_SCHEDULE);
        });

        Schedule schedule = Schedule.of(
                currentMember,
                lecture,
                requestDto.scheduleDate(),
                requestDto.scheduleDate().getDayOfWeek().getValue(),
                requestDto.timeTable(),
                deletable
        );

        scheduleRepository.save(schedule);
    }

    /**
     * 스케줄 삭제 메소드.
     *
     * @param id 스케줄의 pk 값.
     */
    public void deleteSchedule(Long id) {

        // 삭제하려는 스케줄이 존재하지 않는 경우 exception 발생.
        ScheduleCheckDto schedule = scheduleRepository.findScheduleById(id)
                .orElseThrow(() -> new ScheduleException(ScheduleErrorCode.NOT_EXIST_SCHEDULE)
                );

        // 현재 사용자가 존재하지 않는 사용자인 경우 exception 발생.
        String currentUserId = jwtProvider.getCurrentUserId();
        Member currentMember = memberRepository.findById(Long.valueOf(currentUserId))
                .orElseThrow(() -> new ScheduleException(ScheduleErrorCode.NOT_EXIST_MEMBER)
                );

        // 스케줄 삭제를 시도하는 사람이 학생인 경우.
        if (currentMember.getRole() == Role.ROLE_STUDENT) {
            // 스케줄을 추가한 사람이 관리자이거나, 스케줄을 추가한 사람과 삭제하려는 사람이 다른 경우 exception 발생.
            if (!schedule.deletable() || !Objects.equals(schedule.memberId(), currentMember.getId())) {
                throw new ScheduleException(ScheduleErrorCode.NO_AUTH_SCHEDULE_DELETE);
            }
        }

        scheduleRepository.deleteScheduleById(id);
    }
}
