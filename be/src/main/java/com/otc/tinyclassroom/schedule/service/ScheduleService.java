package com.otc.tinyclassroom.schedule.service;

import com.otc.tinyclassroom.global.security.jwt.JwtProvider;
import com.otc.tinyclassroom.lecture.entity.Lecture;
import com.otc.tinyclassroom.lecture.repository.LectureRepository;
import com.otc.tinyclassroom.member.entity.Member;
import com.otc.tinyclassroom.member.entity.MemberClassRoom;
import com.otc.tinyclassroom.member.entity.Role;
import com.otc.tinyclassroom.member.repository.MemberClassRoomRepository;
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
    private final MemberClassRoomRepository memberClassRoomRepository;
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
    public ScheduleListResponseDto getScheduleList(Long memberId, LocalDate scheduleDate) {
        // 멤버 검증
        Member member = getMemberById(memberId);
        // 가장 최근의 멤버 클래스룸 검증 및 선생님 확인
        Member teacher = getClassroomTeacher(member);
        // 스케줄 리스트 가져오기
        List<ScheduleListDto> scheduleList = scheduleRepository.findScheduleListById(memberId, teacher.getMemberId(), scheduleDate);
        return ScheduleListResponseDto.of(scheduleDate, scheduleList);
    }

    /**
     * 스케줄 삽입 메서드.
     *
     * @param requestDto 추가될 스케줄의 정보. 강의 pk이 저장되어 있음.
     */
    public void insertSchedule(ScheduleInsertRequestDto requestDto) {

        // 적용하려는 lecture가 존재하지 않은 경우 에러 발생.
        Lecture lecture = getLectureById(requestDto.lectureId());

        // 현재 사용자가 존재하지 않는 사용자인 경우 exception 발생.
        Long currentUserId = jwtProvider.getCurrentMemberId();
        Member member = getMemberById(currentUserId);

        // 그 멤버가 배정된 반에 할당된 선생이 없을 경우 Exception 발생.
        Member teacher = getClassroomTeacher(member);

        // 사용자 role에 따라 삭제 가능여부를 판단.
        boolean deletable = member.getRole() == Role.ROLE_STUDENT;

        // 시간표에 있는지 확인하기.
        scheduleRepository.findScheduleByMemberIdAndScheduleDateAndTimeTable(
            member.getId(),
            teacher.getMemberId(),
            requestDto.scheduleDate(),
            lecture.getTimeTable()
        ).ifPresent(e -> {
            throw new ScheduleException(ScheduleErrorCode.ALREADY_EXISTED_SCHEDULE);
        });

        Schedule schedule = Schedule.of(
            member,
            lecture,
            requestDto.scheduleDate(),
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
        Long currentUserId = jwtProvider.getCurrentMemberId();
        Member currentMember = getMemberById(currentUserId);

        // 스케줄 삭제를 시도하는 사람이 학생인 경우.
        if (currentMember.getRole() == Role.ROLE_STUDENT) {
            // 스케줄을 추가한 사람이 관리자이거나, 스케줄을 추가한 사람과 삭제하려는 사람이 다른 경우 exception 발생.
            if (!schedule.deletable() || !Objects.equals(schedule.memberId(), currentMember.getId())) {
                throw new ScheduleException(ScheduleErrorCode.NO_AUTH_SCHEDULE_DELETE);
            }
        }

        scheduleRepository.deleteScheduleById(id);
    }

    private Lecture getLectureById(Long lectureId) {
        return lectureRepository.findById(lectureId)
            .orElseThrow(() -> new ScheduleException(ScheduleErrorCode.NOT_EXIST_LECTURE));
    }

    private Member getMemberById(Long memberId) {
        return memberRepository.findById(memberId)
            .orElseThrow(() -> new ScheduleException(ScheduleErrorCode.NOT_EXIST_MEMBER));
    }

    private Member getClassroomTeacher(Member member) {
        List<MemberClassRoom> memberClassRooms = member.getMemberClassRooms();
        if (memberClassRooms.isEmpty()) {
            throw new ScheduleException(ScheduleErrorCode.CLASSROOM_NOT_ASSIGNED);
        }

        MemberClassRoom memberClassroom = memberClassRooms.get(memberClassRooms.size() - 1);
        List<Member> teachers = memberClassRoomRepository.findMemberByClassRoomIdAndRole(memberClassroom.getClassRoom().getId(), Role.ROLE_TEACHER);

        return teachers.stream().findFirst()
            .orElseThrow(() -> new ScheduleException(ScheduleErrorCode.NOT_EXIST_TEACHER));
    }
}
