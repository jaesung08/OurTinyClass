package com.otc.tinyclassroom.attendance.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 지정된 연도, 달의 출석기록을 조회하기 위한 DTO
 *
 * @param attendanceByDate
 */
public record AttendanceCheckMonthDto(List<AttendanceCheckMonthDto.AttendanceMonthInfo> attendanceByDate) implements Serializable {

    public record AttendanceMonthInfo(LocalDate Date, LocalDateTime checkIn, LocalDateTime checkOut, int status) {

    }
}