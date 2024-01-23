package com.otc.tinyclassroom.attendance.dto.response;

import java.sql.Timestamp;

/**
 * 등교시 결과 반환을 위한 DTO
 *
 * @param checkInTime
 * @param status
 */
public record AttendanceCheckInResponseDto(Timestamp checkInTime, int status) {

    // from 메소드 추가
    public static AttendanceCheckInResponseDto from(Timestamp checkInTime, int status) {
        return new AttendanceCheckInResponseDto(checkInTime, status);
    }
}
