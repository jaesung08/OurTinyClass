package com.otc.tinyclassroom.global.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.otc.tinyclassroom.member.entity.Role;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


/**
 * Jwt 토큰 발급을 위한 클래스.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtProvider {

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.token.access-expiration-time}")
    private long accessExpirationTime;

    private static final String TOKEN_PREFIX = "Bearer ";

    // Access Token 생성.
    public String createAccessToken(Long id, Role role) {
        return this.createToken(id, role, accessExpirationTime);
    }

    // Create token
    private String createToken(Long memberId, Role role, long tokenValid) {
        return JWT.create()
                .withExpiresAt(new Date(System.currentTimeMillis() + tokenValid))
                .withClaim("memberId", memberId)
                .withClaim("role", role.name())
                .sign(Algorithm.HMAC512(secret));
    }

    public Long getMemberIdByAccessToken(String token) {
        return JWT.require(Algorithm.HMAC512(secret)).build().verify(token)
            .getClaim("memberId").asLong();
    }

    public String getRoleByAccessToken(String token) {
        return JWT.require(Algorithm.HMAC512(secret)).build().verify(token)
                .getClaim("role").asString();
    }

    /**
     * 현재 로그인 한 사용자를 확인.
     */
    public String getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();

            if (principal instanceof UserDetails) {
                return ((UserDetails) principal).getUsername();
            } else {
                // 만약 principal이 UserDetails가 아닌 다른 타입이면, 해당 타입에 맞게 처리
                return principal.toString();
            }
        }

        return null; // 인증된 사용자가 없는 경우
    }

    /**
     *  토큰 header 확인.
     */
    public String resolveAccessToken(HttpServletRequest request) {
        if (request.getHeader("authorization") != null) {
            return request.getHeader("authorization").replace(TOKEN_PREFIX, "");
        }
        return null;
    }

    // 어세스 토큰 헤더 설정
    public void setHeaderAccessToken(HttpServletResponse response, String accessToken) {
        response.setHeader("authorization", "Bearer " + accessToken);
    }
}
