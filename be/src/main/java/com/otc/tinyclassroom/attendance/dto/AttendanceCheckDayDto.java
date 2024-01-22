package com.otc.tinyclassroom.attendance.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 오늘 하루 출석 기록 반환을 위한 DTO
 */
public record AttendanceCheckDayDto(AttendanceCheckDayDto.AttendanceDayInfo attendanceOnDate) implements Serializable {

    public record AttendanceDayInfo(LocalDate date, LocalDateTime checkIn, LocalDateTime checkOut, int status) {}
}
