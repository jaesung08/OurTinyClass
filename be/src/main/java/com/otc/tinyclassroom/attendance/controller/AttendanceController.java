package com.otc.tinyclassroom.attendance.controller;

import com.otc.tinyclassroom.attendance.dto.AttendanceDto;
import com.otc.tinyclassroom.attendance.service.AttendanceService;
import com.otc.tinyclassroom.global.common.model.response.BaseResponse;
import com.otc.tinyclassroom.member.dto.MemberDto;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 출석 컨트롤러 정의. 멤버 등교, 하교
 *                  출석시간 확인
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping("/check_in")
    public BaseResponse<Void> checkIn(@RequestBody MemberDto memberDto) { // { memberId : "1" }
        String memberId = memberDto.memberId();
        LocalDateTime now = LocalDateTime.now();
        attendanceService.checkIn(memberId, now);
        return BaseResponse.success(HttpStatus.OK.value(), "등교에 성공하였습니다!", null);
    }

    @PutMapping("/check_out")
    public BaseResponse<Void> checkOut(@RequestBody MemberDto memberDto) {
        String memberId = memberDto.memberId();
        LocalDateTime now = LocalDateTime.now();
        attendanceService.checkOut(memberId, now);
        return BaseResponse.success(HttpStatus.OK.value(), "하교에 성공하였습니다!", null);
    }
}
