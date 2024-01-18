package com.otc.tinyclassroom.attendance.controller;

import com.otc.tinyclassroom.attendance.dto.AttendanceDto;
import com.otc.tinyclassroom.attendance.service.AttendanceService;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @PostMapping("/check_in")
    public ResponseEntity<String> checkIn(@RequestParam String memberId) {
        try {
            LocalDateTime now = LocalDateTime.now();
            AttendanceDto attendanceDto = attendanceService.checkIn(memberId, now);

            return ResponseEntity.status(HttpStatus.OK).body("Check-in successful. Status: " + attendanceDto.status());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/check_out")
    public ResponseEntity<String> checkOut(@RequestParam String memberId) {
        try {
            LocalDateTime now = LocalDateTime.now();
            AttendanceDto attendanceDto = attendanceService.checkOut(memberId, now);

            return ResponseEntity.status(HttpStatus.OK).body("Check-out successful. Status: " + attendanceDto.status());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
