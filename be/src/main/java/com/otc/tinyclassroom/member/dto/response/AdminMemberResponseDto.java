package com.otc.tinyclassroom.member.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.otc.tinyclassroom.member.dto.ClassRoomDto;
import com.otc.tinyclassroom.member.entity.Member;
import com.otc.tinyclassroom.member.entity.Role;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Admin 이 관리하기 위한 정보 Dto.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record AdminMemberResponseDto(String memberId, ClassRoomDto classRooms, String name, String email, LocalDate birthday, int point, Role role) implements Serializable {

    public static AdminMemberResponseDto of(String memberId, ClassRoomDto classRooms, String name, String email, LocalDate birthday, int point, Role role) {
        return new AdminMemberResponseDto(memberId, classRooms, name, email, birthday, point, role);
    }

    public static AdminMemberResponseDto from(Member entity) {
        return new AdminMemberResponseDto(
            entity.getMemberId(),
            ClassRoomDto.from(entity.getClassRoom()),
            entity.getName(),
            entity.getEmail(),
            entity.getBirthday(),
            entity.getPoint(),
            entity.getRole()
        );
    }
}