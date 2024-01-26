package com.otc.tinyclassroom.attendance.service;

import com.otc.tinyclassroom.attendance.dto.response.AttendanceCheckInResponseDto;
import com.otc.tinyclassroom.attendance.dto.response.AttendanceCheckOutResponseDto;
import com.otc.tinyclassroom.attendance.dto.response.DailyAttendanceResponseDto;
import com.otc.tinyclassroom.attendance.dto.response.MonthlyAttendanceResponseDto;
import com.otc.tinyclassroom.attendance.entity.Attendance;
import com.otc.tinyclassroom.attendance.entity.type.AttendanceStatus;
import com.otc.tinyclassroom.attendance.exception.AttendanceErrorCode;
import com.otc.tinyclassroom.attendance.exception.AttendanceException;
import com.otc.tinyclassroom.attendance.repository.AttendanceRepository;
import com.otc.tinyclassroom.member.entity.Member;
import com.otc.tinyclassroom.member.repository.MemberRepository;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 출석 서비스.
 */
@Service
@AllArgsConstructor
@Slf4j
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final MemberRepository memberRepository;

    /**
     * 회원 등교를 처리하는 메서드.
     */
    @Transactional
    public AttendanceCheckInResponseDto checkIn(String memberId, LocalDateTime now) {

        System.out.println("memberId1 = " + memberId);
        // 리포지토리에서 회원 정보를 가져옴
        Member member = memberRepository.findByMemberId(memberId)
            .orElseThrow(() -> new AttendanceException(AttendanceErrorCode.NO_MEMBER_ID));

        System.out.println("member = " + member);
        // 회원이 오늘 이미 등교한 경우 예외 처리
        if (hasCheckedInToday(member.getId())) {
            throw new AttendanceException(AttendanceErrorCode.ALREADY_ATTEND);
        }

        System.out.println("memberID2 = " + member.getId());

        // 등교 시간을 확인( 실제 실행시 주석 풀기 )
        //  validateCheckInTime(now);

        AttendanceStatus status = determineAttendanceStatus(now);
        // 등교를 위한 Attendance 엔티티 생성 및 저장
        Attendance attendance = Attendance.of(member, Timestamp.valueOf(now), null, status);
        System.out.println("attendance = " + attendance);
        attendanceRepository.save(attendance);

        return AttendanceCheckInResponseDto.from(Timestamp.valueOf(now), status.ordinal());
    }

    /**
     * 등교 시간을 확인하는 메서드.
     */
    private void validateCheckInTime(LocalDateTime now) {
        int hour = now.getHour();
        // 현재 시간이 허용된 등교 시간 내에 있는지 확인
        // Check-in 및 Check-out 시간을 구성하는 값들
        int checkInStartTime = 8;
        int checkInEndTime = 9;
        if (hour < checkInStartTime || hour >= checkInEndTime) {
            throw new AttendanceException(AttendanceErrorCode.NOT_CHECK_IN_TIME);
        }
    }

    /**
     * 회원이 오늘 이미 등교했는지 확인하는 메서드.
     */
    private boolean hasCheckedInToday(Long memberId) {
        LocalDateTime todayStart = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0)
            .withNano(0);
        LocalDateTime todayEnd = todayStart.plusDays(1);

        // 회원의 등교 기록이 오늘의 시작과 끝 사이에 있는지 확인
        return attendanceRepository.existsByMemberIdAndCheckInBetween(memberId,
            Timestamp.valueOf(todayStart), Timestamp.valueOf(todayEnd));
    }

    /**
     * 등교 시간에 따라 출결 상태를 결정하는 메서드 추가.
     */
    private AttendanceStatus determineAttendanceStatus(LocalDateTime now) {
        int checkInHour = now.getHour();
        int lateThresholdHour = 9;

        return (checkInHour < lateThresholdHour) ? AttendanceStatus.ATTENDANCE : AttendanceStatus.LATE;
    }

    /**
     * 회원 하교를 처리하는 메서드.
     */
    @Transactional
    public AttendanceCheckOutResponseDto checkOut(String memberId, LocalDateTime now) {
        System.out.println("memberId = " + memberId);
        // 리포지토리에서 회원 정보를 가져옴
        Member member = memberRepository.findByMemberId(memberId)
            .orElseThrow(() -> new AttendanceException(AttendanceErrorCode.NO_MEMBER_ID));

        // 회원이 오늘 등교하지 않았는데 하교를 요청한 경우 예외 처리
        if (!hasCheckedInToday(member.getId())) {
            throw new AttendanceException(AttendanceErrorCode.NOT_CHECKED_IN);
        }

        // 오늘 등교한 출결 기록을 가져와서 업데이트
        Attendance attendance = attendanceRepository.findByMemberIdAndCheckInBetween(
            member.getId(),
            Timestamp.valueOf(LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0)),
            Timestamp.valueOf(LocalDateTime.now().plusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0))
        ).orElseThrow(() -> new AttendanceException(AttendanceErrorCode.NOT_CHECKED_IN));

        // 하교시간을 확인하고 status 지정 ( 18시 넘을경우 제한 )
        AttendanceStatus status = validateCheckOutTimeAndDetermineStatus(now, attendance.getCheckIn().toLocalDateTime());

        // 체크아웃 시간을 설정하고 업데이트된 출결 기록을 저장
        attendance.setCheckOut(Timestamp.valueOf(now));
        attendance.setStatus(status);
        attendanceRepository.save(attendance);

        return AttendanceCheckOutResponseDto.from(Timestamp.valueOf(now), status.ordinal());
    }


    /**
     * 하교 시간을 확인하고 상태를 지정해주는 메서드.
     */
    private AttendanceStatus validateCheckOutTimeAndDetermineStatus(LocalDateTime now, LocalDateTime checkInTime) {
        int hour = now.getHour();

        // 현재 시간이 허용된 하교 시간 이후인지 확인
        int checkOutEndTime = 18;
        if (hour >= checkOutEndTime) {
            throw new AttendanceException(AttendanceErrorCode.NOT_CHECK_OUT_TIME);
        }
        // 등교 시간과 하교 시간의 차이를 계산
        Duration duration = Duration.between(checkInTime, now);

        // 차이가 4시간 이내면 결석으로 표시, 그렇지 않으면 조퇴로 표시
        if (duration.toHours() < 4) {
            return AttendanceStatus.ABSENCE;
        } else {
            return AttendanceStatus.LEAVE_EARLY;
        }
    }


    /**
     * 오늘 하루의 출석을 확인하는 메서드.
     */
    @Transactional
    public DailyAttendanceResponseDto getAttendanceTimeOnDate(String memberId, LocalDateTime date) {
        List<Attendance> attendanceListOnDate = attendanceRepository.findByMemberIdAndCheckInBetweenDay(
            memberId,
            Timestamp.valueOf(date.withHour(0).withMinute(0).withSecond(0).withNano(0)),
            Timestamp.valueOf(date.plusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0))
        );

        if (!attendanceListOnDate.isEmpty()) {
            Attendance attendance = attendanceListOnDate.get(0);
            LocalDateTime checkIn = attendance.getCheckIn().toLocalDateTime();
            LocalDateTime checkOut = attendance.getCheckOut() != null ? attendance.getCheckOut().toLocalDateTime() : null;
            int status = attendance.getStatus().ordinal();

            DailyAttendanceResponseDto.AttendanceDayInfo attendanceInfo = new DailyAttendanceResponseDto.AttendanceDayInfo(date.toLocalDate(), checkIn, checkOut, status);
            return new DailyAttendanceResponseDto(attendanceInfo);
        } else {
            // checkIn이 없을 경우 모든 필드를 null로 출력
            DailyAttendanceResponseDto.AttendanceDayInfo attendanceDayInfo = new DailyAttendanceResponseDto.AttendanceDayInfo(date.toLocalDate(), null, null, -1);
            return new DailyAttendanceResponseDto(attendanceDayInfo);
        }
    }

    /**
     * 한달간의 출석내역을 확인하는 메서드.
     */
    @Transactional
    public MonthlyAttendanceResponseDto getAttendanceTimeOnMonth(String memberId, int year, int month) {
        LocalDateTime startOfMonth = LocalDateTime.of(year, month, 1, 0, 0, 0);
        LocalDateTime endOfMonth = startOfMonth.plusMonths(1);
        List<Attendance> monthlyAttendanceList = attendanceRepository.findByMemberIdAndCheckInBetweenMonth(
            memberId,
            Timestamp.valueOf(startOfMonth),
            Timestamp.valueOf(endOfMonth)
        );

        List<MonthlyAttendanceResponseDto.AttendanceMonthInfo> attendanceList = new ArrayList<>();
        for (Attendance attendance : monthlyAttendanceList) {
            LocalDateTime checkIn = attendance.getCheckIn().toLocalDateTime();
            LocalDateTime checkOut = attendance.getCheckOut().toLocalDateTime();
            int status = attendance.getStatus().ordinal();

            LocalDate date = checkIn.toLocalDate();
            MonthlyAttendanceResponseDto.AttendanceMonthInfo attendanceMonthInfo = new MonthlyAttendanceResponseDto.AttendanceMonthInfo(date, checkIn, checkOut, status);
            attendanceList.add(attendanceMonthInfo);
        }

        return new MonthlyAttendanceResponseDto(attendanceList);
    }


}
