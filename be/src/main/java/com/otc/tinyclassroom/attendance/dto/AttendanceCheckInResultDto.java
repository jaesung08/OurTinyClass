package com.otc.tinyclassroom.attendance.dto;

import java.sql.Timestamp;

/**
 * 등교시 결과 반환을 위한 DTO
 *
 * @param checkInTime
 * @param status
 */
public record AttendanceCheckInResultDto(Timestamp checkInTime, int status) {

    // from 메소드 추가
    public static AttendanceCheckInResultDto from(Timestamp checkInTime, int status) {
        return new AttendanceCheckInResultDto(checkInTime, status);
    }
}
