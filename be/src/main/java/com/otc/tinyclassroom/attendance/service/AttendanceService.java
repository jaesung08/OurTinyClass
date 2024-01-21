package com.otc.tinyclassroom.attendance.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.otc.tinyclassroom.attendance.dto.AttendanceDto;
import com.otc.tinyclassroom.attendance.entity.Attendance;
import com.otc.tinyclassroom.attendance.entity.AttendanceStatus;
import com.otc.tinyclassroom.attendance.exception.AttendanceErrorCode;
import com.otc.tinyclassroom.attendance.exception.AttendanceException;
import com.otc.tinyclassroom.attendance.repository.AttendanceRepository;
import com.otc.tinyclassroom.member.entity.Member;
import com.otc.tinyclassroom.member.repository.MemberRepository;
import com.otc.tinyclassroom.member.service.MemberService;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import org.springframework.transaction.annotation.Transactional;

/**
 *  출석 서비스.
 */
@Service
@AllArgsConstructor
@Slf4j
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final MemberService memberService;
    private final MemberRepository memberRepository;

    // 회원 등교를 처리하는 메서드
    @Transactional
    public void checkIn(String memberId, LocalDateTime now) {

        // 리포지토리에서 회원 정보를 가져옴
        Member member = memberRepository.findByMemberId(memberId)
            .orElseThrow(() -> new AttendanceException(AttendanceErrorCode.NO_MEMBER_ID));

        System.out.println("member = " + member);
        // 회원이 오늘 이미 등교한 경우 예외 처리
        if (hasCheckedInToday(member.getId())) {
            throw new AttendanceException(AttendanceErrorCode.ALREADY_ATTEND);
        }

        System.out.println("memberID = " + member.getId());
        // 등교 시간을 확인
//        validateCheckInTime(now);

        // 등교를 위한 Attendance 엔티티 생성 및 저장
        Attendance attendance = Attendance.of(member, Timestamp.valueOf(now), null,
            AttendanceStatus.ATTENDANCE);
        System.out.println("attendance = " + attendance);
        attendanceRepository.save(attendance);
    }

    // 등교 시간을 확인하는 메서드
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

    // 회원이 오늘 이미 등교했는지 확인하는 메서드
    private boolean hasCheckedInToday(Long memberId) {
        LocalDateTime todayStart = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0)
            .withNano(0);
        LocalDateTime todayEnd = todayStart.plusDays(1);

        // 회원의 등교 기록이 오늘의 시작과 끝 사이에 있는지 확인
        return attendanceRepository.existsByMemberIdAndCheckInBetween(memberId,
            Timestamp.valueOf(todayStart), Timestamp.valueOf(todayEnd));
    }

    // 회원 하교를 처리하는 메서드
    @Transactional
    public void checkOut(String memberId, LocalDateTime now) {
        // 리포지토리에서 회원 정보를 가져옴
        Member member = memberRepository.findByMemberId(memberId)
            .orElseThrow(() -> new AttendanceException(AttendanceErrorCode.NO_MEMBER_ID));

        log.info(String.valueOf(member));

        // 회원이 오늘 등교하지 않았는데 하교를 요청한 경우 예외 처리
        if (!hasCheckedInToday(member.getId())) {
            throw new AttendanceException(AttendanceErrorCode.NOT_CHECKED_IN);
        }

        // 하교 시간을 확인
//        validateCheckOutTime(now);

        // 오늘 등교한 출결 기록을 가져와서 업데이트
        Attendance attendance = (Attendance) attendanceRepository.findByMemberIdAndCheckInBetween(
            member.getId(),
            Timestamp.valueOf(
                LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0)),
            Timestamp.valueOf(
                LocalDateTime.now().plusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0))
        ).orElseThrow(() -> new AttendanceException(AttendanceErrorCode.NOT_CHECKED_IN));

        // 체크아웃 시간을 설정하고 업데이트된 출결 기록을 저장
        // ToDo: of메서드 구현해서 다시 하기 ( dirty checking 구현 )
        attendance.setCheckOut(Timestamp.valueOf(now));
        attendanceRepository.save(attendance);
    }


    // 하교 시간을 확인하는 메서드
    private void validateCheckOutTime(LocalDateTime now) {
        int hour = now.getHour();

        // 현재 시간이 허용된 하교 시간 이후인지 확인
        int checkOutEndTime = 18;
        if (hour >= checkOutEndTime) {
            throw new AttendanceException(AttendanceErrorCode.NOT_CHECK_OUT_TIME);
        }
    }

    @Transactional
    public String getAttendanceTimeOnMonth(Long memberId) {
        LocalDateTime startOfMonth = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0)
            .withSecond(0).withNano(0);
        LocalDateTime endOfMonth = startOfMonth.plusMonths(1);

        List<Attendance> monthlyAttendanceList = attendanceRepository.findByMemberIdAndCheckInBetweenMonth(
            memberId,
            Timestamp.valueOf(startOfMonth),
            Timestamp.valueOf(endOfMonth)
        );

        Map<LocalDate, Map<String, String>> attendanceByDate = calculateAttendanceByDate(monthlyAttendanceList);

        return convertAttendanceToJson(attendanceByDate);
    }

    @Transactional
    public String getAttendanceTimeOnDate(Long memberId, LocalDateTime date) {
        List<Attendance> attendanceListOnDate = attendanceRepository.findByMemberIdAndCheckInBetweenDay(
            memberId,
            Timestamp.valueOf(date.withHour(0).withMinute(0).withSecond(0).withNano(0)),
            Timestamp.valueOf(date.plusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0))
        );

        Map<String, Object> attendanceOnDate = new HashMap<>();

        if (!attendanceListOnDate.isEmpty()) {
            Attendance attendance = attendanceListOnDate.get(0);
            attendanceOnDate.put("checkIn", formatTimestamp(attendance.getCheckIn()));
            attendanceOnDate.put("checkOut", formatTimestamp(attendance.getCheckOut()));
        } else {
            attendanceOnDate.put("message", "출결 기록이 없습니다.");
        }

        return convertAttendanceToJson(attendanceOnDate);
    }

    private Map<LocalDate, Map<String, String>> calculateAttendanceByDate(List<Attendance> attendanceList) {
        Map<LocalDate, Map<String, String>> attendanceByDate = new HashMap<>();
        for (Attendance attendance : attendanceList) {
            if (attendance.getCheckIn() != null && attendance.getCheckOut() != null) {
                LocalDate date = attendance.getCheckIn().toLocalDateTime().toLocalDate();
                Map<String, String> attendanceDetails = new HashMap<>();
                attendanceDetails.put("checkIn", formatTimestamp(attendance.getCheckIn()));
                attendanceDetails.put("checkOut", formatTimestamp(attendance.getCheckOut()));

                // 날짜별로 등교와 하교 시간을 구분하여 Map에 담음
                attendanceByDate.computeIfAbsent(date, k -> new HashMap<>());
                attendanceByDate.get(date).put("attendance", attendanceDetails.toString());
            }
        }
        return attendanceByDate;
    }

    private String formatTimestamp(Timestamp timestamp) {
        if (timestamp != null) {
            Date date = new Date(timestamp.getTime());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
                .format(formatter);
        } else {
            return "기록 없음";
        }
    }

    private String convertAttendanceToJson(Object attendanceData) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(attendanceData);
        } catch (JsonProcessingException e) {
            log.error("JSON 변환 중 오류 발생", e);
            throw new AttendanceException(AttendanceErrorCode.NO_ATTENDANCE_RECORD,
                "출결 기록을 JSON으로 변환하는 중 오류가 발생했습니다.");
        }
    }
}
