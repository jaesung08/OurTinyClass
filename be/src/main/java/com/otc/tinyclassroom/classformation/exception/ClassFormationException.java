package com.otc.tinyclassroom.classformation.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 반 편성 관련 exception.
 */
@Getter
@AllArgsConstructor
public class ClassFormationException extends RuntimeException {

    private ClassFormationErrorCode errorCode;
    private String message;

    /**
     * 메세지가 없는 생성자.
     */
    public ClassFormationException(ClassFormationErrorCode errorCode) {
        this.errorCode = errorCode;
        this.message = errorCode.getMessage();
    }
}

