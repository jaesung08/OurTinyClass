package com.otc.tinyclassroom.media.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MediaException extends RuntimeException {

    private MediaErrorCode errorCode;
    private String message;

    public MediaException(MediaErrorCode errorCode) {
        this.errorCode = errorCode;
        this.message = errorCode.getMessage();
    }
}
