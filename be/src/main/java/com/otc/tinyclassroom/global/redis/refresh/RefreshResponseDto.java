package com.otc.tinyclassroom.global.redis.refresh;

/**
 * Refresh Token 을 body 에 담아주기 위한 DTO.
 */
public record RefreshResponseDto(String refreshToken) {
    public static RefreshResponseDto of(String refreshToken) {
        return new RefreshResponseDto(refreshToken);
    }
}
