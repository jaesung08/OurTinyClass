package com.otc.tinyclassroom.global.security.refresh;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;


@Getter
@RedisHash(value = "refreshToken", timeToLive = 1000 * 60 * 60 * 24 * 7)
public class RefreshToken {

    @Id
    private String memberId;

    private String refreshToken;

    public RefreshToken(final String memberId, final String refreshToken) {
        this.memberId = memberId;
        this.refreshToken = refreshToken;
    }

}