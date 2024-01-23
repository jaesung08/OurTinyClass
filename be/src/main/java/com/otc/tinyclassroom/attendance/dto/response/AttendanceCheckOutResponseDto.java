package com.otc.tinyclassroom.attendance.dto.response;

import java.sql.Timestamp;

/**
 * 하교시 결과반환을 위한 DTO
 *
 * @param checkOutTime
 * @param status
 */
public record AttendanceCheckOutResponseDto(Timestamp checkOutTime, int status) {
    // 생성자 및 필요한 메소드들은 record에서 자동 생성됩니다.

    // from 메소드 추가
    public static AttendanceCheckOutResponseDto from(Timestamp checkOutTime, int status) {
        return new AttendanceCheckOutResponseDto(checkOutTime, status);
    }

}
