package com.otc.tinyclassroom.attendance.dto;

import com.otc.tinyclassroom.attendance.entity.AttendanceStatus;
import java.sql.Timestamp;

/**
 * 하교시 결과반환을 위한 DTO
 * @param checkOutTime
 * @param status
 */
public record AttendanceCheckOutResultDto(Timestamp checkOutTime, AttendanceStatus status) {
    // 생성자 및 필요한 메소드들은 record에서 자동 생성됩니다.

    // from 메소드 추가
    public static AttendanceCheckOutResultDto from(Timestamp checkOutTime, AttendanceStatus status) {
        return new AttendanceCheckOutResultDto(checkOutTime, status);
    }

    // equals, hashCode 등 필요한 메소드들은 record에서 자동 생성됩니다.
}
