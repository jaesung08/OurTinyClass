package com.otc.tinyclassroom.member.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 회원 관련 exception.
 */
@Getter
@AllArgsConstructor
public class MemberException extends RuntimeException {

    private MemberErrorCode errorCode;
    private String message;

    /**
     * 메세지가 없는 생성자.
     */
    public MemberException(MemberErrorCode errorCode) {
        this.errorCode = errorCode;
        this.message = null;
    }

    @Override
    public String getMessage() {
        if (message == null) {
            return errorCode.getMessage();
        }
        return String.format("%s %s", errorCode.getMessage(), message);
    }

}
