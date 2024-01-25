package com.otc.tinyclassroom.global.security.jwt;

import com.otc.tinyclassroom.member.entity.Role;

/**
 * 로그인 시 Member 정보를 넘겨주기 위한 Dto.
 */
public record LoginResponseDto(String name, String memberId, int role, int point, String refreshToken) {

    public static LoginResponseDto of(String name, String memberId, Role role, int point, String refreshToken) {
        return new LoginResponseDto(name, memberId, role.getValue(), point, refreshToken);
    }
}
