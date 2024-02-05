package com.otc.tinyclassroom.global.security.refreshtoken.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * RefreshToken 에러 코드.
 */
@Getter
@AllArgsConstructor
public enum RefreshTokenErrorCode {

    NOT_EXIST_TOKEN(HttpStatus.BAD_REQUEST, "존재하지 않는 TOKEN입니다.");

    private final HttpStatus httpStatus;
    private final String message;

}
