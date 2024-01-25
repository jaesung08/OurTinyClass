package com.otc.tinyclassroom.global.security.redis.repository;

import com.otc.tinyclassroom.global.security.redis.entity.RefreshToken;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

/**
 * Redis 를 사용하여 Refresh Token 을 저장하고 조회하는 리포지토리.
 */
@Repository
public class RefreshTokenRepository {

    private RedisTemplate<String, String> redisTemplate;

    @Value("${jwt.token.refresh-expiration-time}")
    private long refreshTokenTtl;


    public RefreshTokenRepository(@Autowired RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * Redis 에 저장된 Refresh Token 정보를 저장소에 저장합.
     */
    public void save(final RefreshToken refreshToken) {

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

        String result = redisTemplate.opsForValue().get(refreshToken).toString();
        if (result.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(result);
    }
}