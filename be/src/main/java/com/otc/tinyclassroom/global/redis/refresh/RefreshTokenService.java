package com.otc.tinyclassroom.global.redis.refresh;

import com.otc.tinyclassroom.global.security.jwt.JwtProvider;
import com.otc.tinyclassroom.member.entity.Role;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final RedisTemplate<String, String> redisTemplate;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtProvider jwtProvider;
    public void save(String memberId, String refreshToken_String) {
        RefreshToken refreshToken = new RefreshToken(refreshToken_String, memberId);
        refreshTokenRepository.save(refreshToken);
    }

    public Optional<String> findByMemberId(final String memberId) {
        if(refreshTokenRepository.findByMemberId(memberId).isEmpty()){
            return Optional.empty();
        }
        return Optional.of(String.valueOf(refreshTokenRepository.findByMemberId(memberId)));
    }

    public Long getTTLByMemberId(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    public ReIssueResponseDto reIssue(String accessToken, String refreshToken) {
        // TODO : 여기서 문제 터짐
        System.out.println("accessToken = " + accessToken);
        System.out.println("refreshToken = " + refreshToken);
        Long memberId = jwtProvider.getMemberIdByAccessToken(accessToken);
        System.out.println("memberId = " + memberId);
        Optional<String> refreshToken_inRedis = this.findByMemberId(String.valueOf(memberId));
        System.out.println("refreshToken_inRedis = " + refreshToken_inRedis);
        if (String.valueOf(refreshToken_inRedis).equals(refreshToken)) {
            Role role = Role.valueOf(jwtProvider.getRoleByAccessToken(accessToken));
            System.out.println("role = " + role);
            refreshToken = UUID.randomUUID().toString();
            System.out.println("refreshToken = " + refreshToken);
            RefreshToken toRedis = new RefreshToken(refreshToken, memberId.toString());
            System.out.println("toRedis = " + toRedis);
            refreshTokenRepository.save(toRedis);

            String newAccessToken = jwtProvider.createAccessToken(memberId, role);
            System.out.println("newAccessToken = " + newAccessToken);
            return ReIssueResponseDto.of(toRedis.getRefreshToken(), newAccessToken);
        } else {
            return null;
        }
    }
}
