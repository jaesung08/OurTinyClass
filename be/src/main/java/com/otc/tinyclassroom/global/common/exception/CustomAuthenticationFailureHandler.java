package com.otc.tinyclassroom.global.common.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.otc.tinyclassroom.global.common.model.response.BaseResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        int errorStatus = HttpStatus.UNAUTHORIZED.value();
        String errorMessage = null;

        if(exception instanceof BadCredentialsException || exception instanceof InternalAuthenticationServiceException){
//            errorStatus = HttpStatus.NOT_FOUND.value();
            errorStatus = HttpStatus.NOT_FOUND.value();
            errorMessage = "Username과 Password가 맞지 않습니다. 다시 확인해 주십시오";
        }else if(exception instanceof DisabledException){
            errorMessage = "계정이 비활성화 되었습니다. 관리자에게 문의하세요.";
        }else if(exception instanceof CredentialsExpiredException){
            errorMessage = "비밀번호 유효기간이 만료 되었습니다. 관리자에게 문의하세요.";
        }else{
            errorMessage = "알 수 없는 이유로 로그인에 실패하였습니다. 관리자에게 문의하세요.";
        }

        BaseResponse<Void> responseDto = new BaseResponse<>(errorStatus, errorMessage, null);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new ObjectMapper().writeValueAsString(responseDto));
    }
}
