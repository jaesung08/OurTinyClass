package com.otc.tinyclassroom.attendance.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 출석관련 exception
 */
@Getter
@AllArgsConstructor
public class AttendanceException  extends RuntimeException {

    private AttendanceErrorCode errorCode;
    private String message;

    // exception을 GlobalControllerAdvice에 추가해줘야 정확하게 작동한다.
    public AttendanceException(AttendanceErrorCode errorCode) {
            this.errorCode = errorCode;
            this.message = errorCode.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }
}


