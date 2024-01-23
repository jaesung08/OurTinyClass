package com.otc.tinyclassroom.global.redis.refresh;

public record ReIssueResponseDto(String refreshToken, String accessToken) {
    public static ReIssueResponseDto of(String refreshToken, String accessToken) {
        return new ReIssueResponseDto(refreshToken, accessToken);
    }
}
