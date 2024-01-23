package com.otc.tinyclassroom.global.common.exception;

import com.otc.tinyclassroom.global.common.model.response.BaseResponse;
import com.otc.tinyclassroom.member.exception.MemberException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.security.SignatureException;

/**
 * 전체 Exception Handler.
 */
@Slf4j
@RestControllerAdvice
public class GlobalControllerAdvice {

    /**
     *  Member Exception Handler.
     */
    @ExceptionHandler(MemberException.class)
    public ResponseEntity<?> applicationHandler(MemberException e) {
        log.error("Error occurs {}", e.toString());
        return ResponseEntity.status(e.getErrorCode().getHttpStatus())
            .body(BaseResponse.error(e.getErrorCode().getHttpStatus().value(), e.getMessage()));
    }
    /*
    *
    * */
//    @ExceptionHandler(SignatureException.class)
//    public ResponseEntity<?> handleSignatureException() {
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                .body(BaseResponse.error(HttpStatus.UNAUTHORIZED.value(), "토큰이 유효하지 않습니다."));
//    }
//
//    @ExceptionHandler(AccessDeniedException.class)
//    public ResponseEntity<?> handleAccessDeniedException() {
//        return ResponseEntity.status(HttpStatus.FORBIDDEN)
//                .body(BaseResponse.error(HttpStatus.FORBIDDEN.value(), "접근 권한이 없습니다."));
//    }
//
//    @ExceptionHandler(MalformedJwtException.class)
//    public ResponseEntity<ApiResponse> handleMalformedJwtException() {
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ApiResponse.error("올바르지 않은 토큰입니다."));
//    }
//
//    @ExceptionHandler(ExpiredJwtException.class)
//    public ResponseEntity<ApiResponse> handleExpiredJwtException() {
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ApiResponse.error("토큰이 만료되었습니다. 다시 로그인해주세요."));
//    }

}
