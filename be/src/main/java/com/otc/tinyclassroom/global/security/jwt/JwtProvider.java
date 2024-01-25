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
