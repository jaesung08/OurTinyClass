package com.otc.tinyclassroom.attendance.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AttendanceException  extends RuntimeException {

    private AttendanceErrorCode errorCode;
    private String message;

    public AttendanceException(AttendanceErrorCode errorCode) {
//        super(message);
        this.errorCode = errorCode;
        this.message = errorCode.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }
}


