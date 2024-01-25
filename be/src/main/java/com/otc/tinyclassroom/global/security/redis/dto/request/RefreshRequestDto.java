package com.otc.tinyclassroom.global.security.redis.dto.request;

import com.otc.tinyclassroom.global.security.redis.dto.response.ReIssueResponseDto;

/**
 * Refresh 요청을 수행하기 위한 RequestDto.
 */
public record RefreshRequestDto(String refreshToken, String accessToken) {
    public static ReIssueResponseDto of(String refreshToken, String accessToken) {
        return new ReIssueResponseDto(refreshToken, accessToken);
    }
}
