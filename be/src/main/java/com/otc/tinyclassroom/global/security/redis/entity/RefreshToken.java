package com.otc.tinyclassroom.global.security.redis.entity;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

/**
 * RefreshToken 은 Redis 로 따로 관리하기 위해 분리.
 */
@Getter
@RedisHash(value = "refreshToken")
public class RefreshToken {

    @Id
    private String refreshToken;
    private final String memberId;

    public RefreshToken(String refreshToken, String memberId) {
        this.refreshToken = refreshToken;
        this.memberId = memberId;
    }
}