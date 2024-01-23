package com.otc.tinyclassroom.global.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.otc.tinyclassroom.global.common.model.response.BaseResponse;
import com.otc.tinyclassroom.global.redis.refresh.RefreshToken;
import com.otc.tinyclassroom.global.redis.refresh.RefreshTokenRepository;
import com.otc.tinyclassroom.global.security.auth.PrincipalDetails;
import com.otc.tinyclassroom.member.dto.request.MemberLoginRequestDto;
import com.otc.tinyclassroom.member.entity.Member;
import com.otc.tinyclassroom.member.exception.MemberErrorCode;
import com.otc.tinyclassroom.member.exception.MemberException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * Spring Security 를 사용하기 위한 Authentication Filter.
 */
@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtProvider jwtProvider;

    /**
     *  AuthenticationFilter 생성자.
     */
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
        } catch (MemberException e) {
            log.info("MemberException: {}", e.toString());
            throw new MemberException(MemberErrorCode.NOT_FOUND_MEMBER_ID);
        } catch (Exception e) {
            log.info("Exception: {}", e.toString());
            throw new RuntimeException("서버에서 오류가 발생했습니다.", e);
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
        LoginResponseDto loginResponseDto = new LoginResponseDto(loginMember.getName(), loginMember.getMemberId(), loginMember.getRole(), loginMember.getPoint());
        setTokenResponse(response, loginResponseDto);

        jwtProvider.setHeaderAccessToken(response, accessToken);
    }

    private void setTokenResponse(HttpServletResponse response, LoginResponseDto loginResponseDto) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        BaseResponse<LoginResponseDto> responseDto = new BaseResponse<>(200, "로그인 성공", loginResponseDto);

        response.getWriter().write(new ObjectMapper().writeValueAsString(responseDto));
    }
}

