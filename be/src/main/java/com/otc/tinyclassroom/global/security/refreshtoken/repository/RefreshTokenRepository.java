package com.otc.tinyclassroom.global.security.refreshtoken.repository;

import com.otc.tinyclassroom.global.security.refreshtoken.entity.RefreshToken;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

/**
 * Redis 를 사용하여 Refresh Token 을 저장하고 조회하는 리포지토리.
 */
@Repository
public class RefreshTokenRepository {

    private final RedisTemplate<String, String> redisTemplate;

    @Value("${jwt.token.refresh-expiration-time}")
    private long refreshTokenTtl;

    public RefreshTokenRepository(final RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * Redis 에 저장된 Refresh Token 정보를 저장소에 저장합.
     */
    public void save(RefreshToken refreshToken) {

        redisTemplate.opsForValue().set(
            refreshToken.getRefreshToken(),
            refreshToken.getMemberId(),
            refreshTokenTtl,
            TimeUnit.MILLISECONDS
        );
    }

    /**
     *  Refresh Token 에 해당하는 memberId 조회.
     */
    public Optional<String> findByRefreshToken(final String refreshToken) {

        String result = Optional.ofNullable(redisTemplate.opsForValue().get(refreshToken))
            .map(Object::toString)
            .orElseThrow(() -> new NoSuchElementException("Refresh token 이 존재하지 않습니다."));

        if (result.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(result);
    }
}