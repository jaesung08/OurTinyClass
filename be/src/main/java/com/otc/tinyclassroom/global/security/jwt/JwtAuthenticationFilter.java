package com.otc.tinyclassroom.global.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.otc.tinyclassroom.global.common.exception.CustomAuthenticationFailureHandler;
import com.otc.tinyclassroom.global.common.model.response.BaseResponse;
import com.otc.tinyclassroom.global.redis.refresh.RefreshToken;
import com.otc.tinyclassroom.global.redis.refresh.RefreshTokenRepository;
import com.otc.tinyclassroom.global.security.auth.PrincipalDetails;
import com.otc.tinyclassroom.member.dto.request.MemberLoginRequestDto;
import com.otc.tinyclassroom.member.entity.Member;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;


@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtProvider jwtProvider;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, RefreshTokenRepository refreshTokenRepository, JwtProvider jwtProvider) {
        this.authenticationManager = authenticationManager;
        this.refreshTokenRepository = refreshTokenRepository;
        this.jwtProvider = jwtProvider;
        this.setFilterProcessesUrl("/api/members/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        log.info("JwtAuthentication : 진입");

        ObjectMapper objectMapper = new ObjectMapper();
        MemberLoginRequestDto loginRequestDto = null;

        try {
            loginRequestDto = objectMapper.readValue(request.getInputStream(), MemberLoginRequestDto.class);
            log.info("try loginDto : {}", loginRequestDto);

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(loginRequestDto.getLoginId(), loginRequestDto.getPassword());

            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
            log.info("Authentication : {}", principalDetails.getMember().getMemberId());

            return authentication;
        }

        catch (Exception e) {
            log.info("Exception : {}", e.toString());
            throw new RuntimeException("존재하지 않는 아이디입니다.");
        }
    }

    // jwt 토큰 생성 및 response 로 반환
    @Override
    protected void successfulAuthentication(
            HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException {

        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();

        Member loginMember = principalDetails.getMember();
        String accessToken = jwtProvider.createAccessToken(loginMember.getId(), loginMember.getRole());
        String refreshToken = UUID.randomUUID().toString();
        RefreshToken toRedis = new RefreshToken(refreshToken, loginMember.getId().toString());

        refreshTokenRepository.save(toRedis);
        setTokenResponse(response, accessToken, refreshToken);

        jwtProvider.setHeaderAccessToken(response, accessToken);
    }

    private void setTokenResponse(HttpServletResponse response, String accessToken, String refreshToken) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Map<String, Object> result = new HashMap<>();
        result.put("accessToken", accessToken);
        result.put("refreshToken", refreshToken);

        BaseResponse<Map<String, Object>> responseDto = new BaseResponse<>(200, "로그인 성공", result);

        response.getWriter().write(new ObjectMapper().writeValueAsString(responseDto));
    }
}

