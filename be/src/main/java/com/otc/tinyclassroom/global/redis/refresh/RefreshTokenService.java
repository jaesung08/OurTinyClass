package com.otc.tinyclassroom.global.redis.refresh;

import com.otc.tinyclassroom.global.security.jwt.JwtProvider;
import com.otc.tinyclassroom.member.entity.Role;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Refresh Token 과 관련된 비즈니스 로직을 처리하는 서비스 클래스.
 */
@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final RedisTemplate<String, String> redisTemplate;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtProvider jwtProvider;

    /**
     * 회원 식별자와 Refresh Token 을 받아서 저장.
     */
    public void save(String memberId, String refreshTokenString) {
        RefreshToken refreshToken = new RefreshToken(refreshTokenString, memberId);
        refreshTokenRepository.save(refreshToken);
    }

    /**
     *  회원 식별자에 해당하는 Refresh Token 을 조회.
     */
    public String findByMemberId(final String memberId) {
        Optional<String> byMemberId = refreshTokenRepository.findByMemberId(memberId);

        // 값이 있으면 해당 값을 반환, 값이 없으면 기본값(또는 원하는 동작)을 반환
        return byMemberId.orElse(null); // 또는 다른 기본값 또는 동작을 지정할 수 있음
    }

    public Long getTtlByMemberId(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * Access Token과 Refresh Token을 사용하여 새로운 Access Token을 발급합니다.
     */
    public Optional<ReIssueResponseDto> reIssue(String accessToken, String refreshToken) {
        Long memberId = jwtProvider.getMemberIdByAccessToken(accessToken);

        String refreshTokenInRedis = this.findByMemberId(String.valueOf(memberId));

        if (String.valueOf(refreshTokenInRedis).equals(refreshToken)) {
            Role role = Role.valueOf(jwtProvider.getRoleByAccessToken(accessToken));
            refreshToken = UUID.randomUUID().toString();
            RefreshToken toRedis = new RefreshToken(refreshToken, memberId.toString());
            refreshTokenRepository.save(toRedis);

            String newAccessToken = jwtProvider.createAccessToken(memberId, role);
            return Optional.of(ReIssueResponseDto.of(toRedis.getRefreshToken(), newAccessToken));
        } else {
            return Optional.empty();
        }
    }

    public void deleteRefreshToken(Long memberId) {
        redisTemplate.delete(String.valueOf(memberId));
    }
}
