package com.otc.tinyclassroom.lecture.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 강의 에러코드.
 */
@Getter
@AllArgsConstructor
public enum LectureErrorCode {

    // DailyCo에 잘못된 Error 보낼 때
    INVALID_REQUEST_ERROR(HttpStatus.BAD_REQUEST, "서버 에러");

    private final HttpStatus httpStatus;
    private final String message;
}
