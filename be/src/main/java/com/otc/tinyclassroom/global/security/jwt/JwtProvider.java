package com.otc.tinyclassroom.global.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.otc.tinyclassroom.member.entity.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.xml.bind.DatatypeConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtProvider {

    private String secret = "qwerasdfzxcvqwerasdfz";
    private long accessExpirationTime = 1000 * 10;  // 10 초
    private long refreshExprationTime = 1000 * 3600 * 24; // 1일

    private String TOKEN_PREFIX = "Bearer ";

    // Access Token 생성.
    public String createAccessToken(String memberId, Role role){
        return this.createToken(memberId, role, accessExpirationTime);
    }
    // Refresh Token 생성.
    public String createRefreshToken(String memberId, Role role) {
        return this.createToken(memberId, role, refreshExprationTime);
    }

    // Create token
    private String createToken(String memberId, Role role, long tokenValid) {
        return JWT.create()
                .withSubject(memberId)
                .withExpiresAt(new Date(System.currentTimeMillis() + tokenValid))
                .withClaim("memberId", memberId)
                .withClaim("role", role.name())
                .sign(Algorithm.HMAC512(secret));
    }

    public String getMemberIdByAccessToken(String token) {
        return JWT.require(Algorithm.HMAC512(secret)).build().verify(token)
                .getClaim("memberId").asString();
    }

    public String getRoleByAccessToken(String token) {
        return JWT.require(Algorithm.HMAC512(secret)).build().verify(token)
                .getClaim("role").asString();
    }

    public boolean isExpired(String token) {
//        Date expireDate =
        return false;
    }


    public String resolveAccessToken(HttpServletRequest request) {
        if(request.getHeader("authorization") != null )
            return request.getHeader("authorization").replace(TOKEN_PREFIX, "");

        return null;
    }

    // Request의 Header에서 RefreshToken 값을 가져옵니다. "authorization" : "token'
    public String resolveRefreshToken(HttpServletRequest request) {
        if(request.getHeader("refresh") != null )
            return request.getHeader("refresh").replace(TOKEN_PREFIX, "");

        return null;
    }

    // 어세스 토큰 헤더 설정
    public void setHeaderAccessToken(HttpServletResponse response, String accessToken) {
        response.setHeader("authorization", "Bearer "+ accessToken);
    }

    // 리프레시 토큰 헤더 설정
    public void setHeaderRefreshToken(HttpServletResponse response, String refreshToken) {
        response.setHeader("refresh", "Refresh " + refreshToken);
    }

    // RefreshToken 존재유무 확인
    public boolean existsRefreshToken(String refreshToken) {
//        return tokenRepository.existsByRefreshToken(refreshToken);
        return true;
    }
}
