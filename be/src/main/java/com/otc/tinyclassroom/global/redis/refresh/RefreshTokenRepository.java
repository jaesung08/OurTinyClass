package com.otc.tinyclassroom.global.redis.refresh;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

/**
 * Redis 를 사용하여 Refresh Token 을 저장하고 조회하는 리포지토리.
 */
@Repository
public class RefreshTokenRepository {

    private RedisTemplate redisTemplate;

    @Value("${jwt.token.refresh-expiration-time}")
    private long refreshTokenTtl;

    public RefreshTokenRepository(final RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * Redis 에 저장된 Refresh Token 정보를 저장소에 저장합.
     */
    public void save(final RefreshToken refreshToken) {

        redisTemplate.opsForValue().set(
            refreshToken.getMemberId(),
            refreshToken.getRefreshToken(),
            refreshTokenTtl,
            TimeUnit.MILLISECONDS
        );
    }

    /**
     * 회원 식별자에 해당하는 Refresh Token을 조회.
     */
    public Optional<String> findByMemberId(final String memberId) {
        if (redisTemplate.opsForValue().get(memberId) == null) {
            return Optional.empty();
        }
        return Optional.of(String.valueOf(redisTemplate.opsForValue().get(memberId)));
    }
}