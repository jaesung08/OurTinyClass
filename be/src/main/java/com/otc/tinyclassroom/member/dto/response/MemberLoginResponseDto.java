package com.otc.tinyclassroom.member.dto.response;

import com.otc.tinyclassroom.member.entity.Role;

/**
 * 로그인 시 Member 정보를 넘겨주기 위한 Dto.
 */
public record MemberLoginResponseDto(String name, String memberId, int role, int point, String refreshToken) {

    public static MemberLoginResponseDto of(String name, String memberId, Role role, int point, String refreshToken) {
        return new MemberLoginResponseDto(name, memberId, role.getValue(), point, refreshToken);
    }
}
