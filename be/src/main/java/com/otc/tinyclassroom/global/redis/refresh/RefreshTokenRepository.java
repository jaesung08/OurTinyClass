package com.otc.tinyclassroom.global.redis.refresh;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

@Repository
public class RefreshTokenRepository {

    private RedisTemplate redisTemplate;

    @Value("${jwt.token.refresh-expiration-time}")
    private long refreshTokenTTL;

    public RefreshTokenRepository(final RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void save(final RefreshToken refreshToken) {

        redisTemplate.opsForValue().set(
            refreshToken.getMemberId(),
            refreshToken.getRefreshToken(),
            refreshTokenTTL,
            TimeUnit.MILLISECONDS
        );
    }

    public Optional<String> findByMemberId(final String memberId) {
        System.out.println("repo에서: " + redisTemplate.opsForValue().get(memberId));
        if (redisTemplate.opsForValue().get(memberId) == null){
            return Optional.empty();
        }
        return Optional.of(String.valueOf(redisTemplate.opsForValue().get(memberId)));
    }
}