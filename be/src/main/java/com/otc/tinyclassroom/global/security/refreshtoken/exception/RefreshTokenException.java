package com.otc.tinyclassroom.global.security.refreshtoken.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * RefreshToken 관련 Exception.
 */
@Getter
@AllArgsConstructor
public class RefreshTokenException extends RuntimeException {

    private RefreshTokenErrorCode errorCode;
    private String message;

    public RefreshTokenException(RefreshTokenErrorCode errorCode) {
        this.errorCode = errorCode;
        this.message = errorCode.getMessage();
    }
}