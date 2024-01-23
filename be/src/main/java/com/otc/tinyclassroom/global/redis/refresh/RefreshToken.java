package com.otc.tinyclassroom.global.redis.refresh;

import java.util.concurrent.TimeUnit;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

/**
 * RefreshToken 은 Redis 로 따로 관리하기 위해 분리.
 */
@Getter
@RedisHash(value = "refreshToken")
public class RefreshToken {

    @Id
    private String refreshToken;

    private String memberId;

    public RefreshToken(String refreshToken, String memberId) {
        this.refreshToken = refreshToken;
        this.memberId = memberId;
    }
}