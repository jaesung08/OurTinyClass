package com.otc.tinyclassroom.attendance.dto;

import com.otc.tinyclassroom.attendance.entity.AttendanceStatus;
import java.sql.Timestamp;

/**
 * 등교시 결과 반환을 위한 DTO
 * @param checkInTime
 * @param status
 */
public record AttendanceCheckInResultDto(Timestamp checkInTime, AttendanceStatus status) {

    // from 메소드 추가
    public static AttendanceCheckInResultDto from(Timestamp checkInTime, AttendanceStatus status) {
        return new AttendanceCheckInResultDto(checkInTime, status);
    }

}
