package com.otc.tinyclassroom.attendance.dto;

import com.otc.tinyclassroom.attendance.entity.Attendance;
import com.otc.tinyclassroom.member.dto.MemberDto;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * DTO for {@link Attendance}
 */
public record AttendanceDto(MemberDto member, Timestamp checkIn, Timestamp checkOut, com.otc.tinyclassroom.attendance.entity.AttendanceStatus status) implements Serializable {

    public static AttendanceDto fromEntity(Attendance attendance) {
        return new AttendanceDto(
            MemberDto.from(attendance.getMember()), // MemberDto 변환 메소드 호출
            attendance.getCheck_in(),
            attendance.getCheck_out(),
            attendance.getStatus()
        );
    }
}