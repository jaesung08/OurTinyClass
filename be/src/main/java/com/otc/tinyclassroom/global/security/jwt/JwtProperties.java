package com.otc.tinyclassroom.global.security.jwt;

public interface JwtProperties {
    String SECRET = "123";
    int EXPIRATION_TIME = 1000 * 60 * 10; // 10분
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
}
