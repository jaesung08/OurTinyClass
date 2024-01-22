package com.otc.tinyclassroom.attendance.controller;

import com.otc.tinyclassroom.attendance.dto.AttendanceCheckDayDto;
import com.otc.tinyclassroom.attendance.dto.AttendanceCheckInResultDto;
import com.otc.tinyclassroom.attendance.dto.AttendanceCheckMonthDto;
import com.otc.tinyclassroom.attendance.dto.AttendanceCheckOutResultDto;
import com.otc.tinyclassroom.attendance.service.AttendanceService;
import com.otc.tinyclassroom.global.common.model.response.BaseResponse;
import com.otc.tinyclassroom.member.dto.MemberDto;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 출석 컨트롤러 정의. 멤버 등교, 하교 멤버 출석시간 확인
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/attendances")
public class AttendanceController {

    private final AttendanceService attendanceService;

    // Todo : 토큰 받아서 진행시 토큰 처리식으로 변경하기
    // 등교시간 기록
    @PostMapping("/check-in")
    public BaseResponse<AttendanceCheckInResultDto> checkIn(@RequestBody MemberDto memberDto) {
        String memberId = memberDto.memberId();
        LocalDateTime now = LocalDateTime.now();
        AttendanceCheckInResultDto result = attendanceService.checkIn(memberId, now);
        return BaseResponse.success(HttpStatus.OK.value(), "등교에 성공하였습니다!", result);
    }

    // 하교시간 기록
    @PutMapping("/check-out")
    public BaseResponse<AttendanceCheckOutResultDto> checkOut(@RequestBody MemberDto memberDto) {
        String memberId = memberDto.memberId();
        LocalDateTime now = LocalDateTime.now();
        AttendanceCheckOutResultDto result = attendanceService.checkOut(memberId, now);
        return BaseResponse.success(HttpStatus.OK.value(), "하교에 성공하였습니다!", result);
    }

    // Todo : 토큰받고 memberId 가져와서 진행하는 방향으로 refactoring
    // 오늘 출석기록 조회
    @GetMapping("/today-attendance/{memberId}")
    public BaseResponse<AttendanceCheckDayDto> getDailyAttendanceTime(
        @PathVariable("memberId") String memberId
    ) {
        LocalDateTime today = LocalDateTime.now();
        AttendanceCheckDayDto result = attendanceService.getAttendanceTimeOnDate(memberId, today);
        String message = String.format("%s의 출석 조회에 성공하였습니다.", today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        return BaseResponse.success(HttpStatus.OK.value(), message, result);
    }

    // 한달치 출석기록 조회
    @GetMapping("/monthly-total/{memberId}/{year}/{month}")
    public BaseResponse<AttendanceCheckMonthDto> getMonthlyTotalAttendanceTime(
        @PathVariable("memberId") String memberId,
        @PathVariable("year") int year,
        @PathVariable("month") int month
    ) {
        AttendanceCheckMonthDto result = attendanceService.getAttendanceTimeOnMonth(memberId, year, month);
        String message = String.format("%d년 %d월 출석 조회에 성공하였습니다.", year, month);
        return BaseResponse.success(HttpStatus.OK.value(), message, result);
    }
}
