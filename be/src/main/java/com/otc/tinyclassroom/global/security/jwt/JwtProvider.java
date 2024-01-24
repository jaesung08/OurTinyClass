package com.otc.tinyclassroom.global.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.otc.tinyclassroom.member.entity.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import java.util.Date;


@Slf4j
@Component
@RequiredArgsConstructor
public class JwtProvider {

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.token.access-expiration-time}")
    private long accessExpirationTime;
    @Value("${jwt.token.refresh-expiration-time}")
    private long refreshExpirationTime; // 1일

    private String TOKEN_PREFIX = "Bearer ";

    // Access Token 생성.
    public String createAccessToken(Long id, Role role){
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

    public boolean isExpired(String token) {
        // TODO : 만료 구현
        return false;
    }
    // 토큰의 유효성 + 만료일자 확인
    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (ExpiredJwtException e) {
            log.info(e.getMessage());
            return false;
        }
    }


    public String resolveAccessToken(HttpServletRequest request) {
        // TODO : Exception 터뜨리게
        if (request.getHeader("authorization") != null) {
            return request.getHeader("authorization").replace(TOKEN_PREFIX, "");
        }
        return null;
    }

    // 어세스 토큰 헤더 설정
    public void setHeaderAccessToken(HttpServletResponse response, String accessToken) {
        response.setHeader("authorization", "Bearer "+ accessToken);
    }

    // RefreshToken 존재유무 확인
    public boolean existsRefreshToken(String refreshToken) {
        // TODO : RefreshToken 존재 유무 확인
        return true;
    }
}
