package com.otc.tinyclassroom.attendance.dto.response;

import java.sql.Timestamp;

/**
 * 등교시 결과 반환을 위한 DTO.
 */
public record AttendanceCheckInResponseDto(Timestamp checkInTime, int status) {

    /**
     * AttendanceCheckInResponseDto 객체를 생성하고 반환.
     */
    public static AttendanceCheckInResponseDto from(Timestamp checkInTime, int status) {
        return new AttendanceCheckInResponseDto(checkInTime, status);
    }
}
