package com.otc.tinyclassroom.member.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 회원 에러 코드.
 */
@Getter
@AllArgsConstructor
public enum MemberErrorCode {
    // 회원가입 에러 코드
    INVALID_FIELD_VALUE(HttpStatus.BAD_REQUEST, "빈 필드값이 있습니다."),
    DUPLICATED_USER_NAME(HttpStatus.CONFLICT, "중복된 아이디입니다."),
    INVALID_EMAIL(HttpStatus.BAD_REQUEST, "이메일 형식이 올바르지 않습니다."),
    PASSWORD_VALIDATION_FAILED(HttpStatus.BAD_REQUEST, "비밀번호는 8~20자리이며 특수문자, 영어 대소문자, 숫자를 포함해야 합니다.");

    private final HttpStatus httpStatus;
    private final String message;

}
