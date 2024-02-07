package com.otc.tinyclassroom.schedule.service;

import com.otc.tinyclassroom.global.security.jwt.JwtProvider;
import com.otc.tinyclassroom.member.entity.Member;
import com.otc.tinyclassroom.member.entity.Role;
import com.otc.tinyclassroom.member.repository.MemberRepository;
import com.otc.tinyclassroom.schedule.dto.ScheduleCheckDto;
import com.otc.tinyclassroom.schedule.dto.ScheduleListDto;
import com.otc.tinyclassroom.schedule.dto.request.ScheduleInsertRequestDto;
import com.otc.tinyclassroom.schedule.dto.response.ScheduleListResponseDto;
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
        List<ScheduleListDto> scheduleList = scheduleRepository.findScheduleListByMemberId(memberId, scheduleDate);
        return ScheduleListResponseDto.of(scheduleDate, scheduleList);
    }

    /**
     * 스케줄 삽입 메서드.
     *
     * @param requestDto 추가될 스케줄의 정보. 강의 pk이 저장되어 있음.
     */
    public void insertSchedule(ScheduleInsertRequestDto requestDto) {

        // TODO : 강의가 존재하는지 검증하고 강의 엔티티를 가져오는 부분.

        // TODO : 강의 엔티티와 reqeustDto를 이용해 스케줄 엔티티를 만드는 부분.

        scheduleRepository.insertSchedule();
    }

    /**
     * 스케줄 삭제 메소드.
     *
     * @param id 스케줄의 pk 값.
     */
    public void deleteSchedule(Long id) {

        // 삭제하려는 스케줄이 존재하지 않는 경우 exception 발생.
        ScheduleCheckDto schedule = scheduleRepository.findScheduleById(id)
            .orElseThrow(() -> new ScheduleException(ScheduleErrorCode.NOT_FOUND_LECTURE)
            );

        // 현재 사용자가 존재하지 않는 사용자인 경우 exception 발생.
        String currentUserId = jwtProvider.getCurrentUserId();
        Member currentMember = memberRepository.findById(Long.valueOf(currentUserId))
            .orElseThrow(() -> new ScheduleException(ScheduleErrorCode.NOT_FOUND_MEMBER)
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
