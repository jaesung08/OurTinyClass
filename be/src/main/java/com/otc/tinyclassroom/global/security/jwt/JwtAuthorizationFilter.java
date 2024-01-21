package com.otc.tinyclassroom.global.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.otc.tinyclassroom.global.common.model.response.BaseResponse;
import com.otc.tinyclassroom.global.security.auth.PrincipalDetails;
import com.otc.tinyclassroom.member.entity.Member;
import com.otc.tinyclassroom.member.repository.MemberRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private MemberRepository memberRepository;
    private JwtProvider jwtProvider;

    public JwtAuthorizationFilter(MemberRepository memberRepository, JwtProvider jwtProvider) {
        this.memberRepository = memberRepository;
        this.jwtProvider = jwtProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws IOException, ServletException {

        String servletPath = request.getServletPath();
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        BaseResponse<Void> responseDto = null;

        // 로그인, 리프레시 요청이라면 토큰 검사하지 않음
        if (servletPath.equals("/api/members/login") || servletPath.equals("/api/members/refresh")) {
            chain.doFilter(request, response);
        } else if (header == null || !header.startsWith("Bearer ")) {
            // 토큰값이 없거나 정상적이지 않다면 400 오류
            log.info("CustomAuthorizationFilter : JWT Token이 존재하지 않습니다.");

            responseDto = new BaseResponse<>(HttpStatus.BAD_REQUEST.value(), "access token이 존재하지 않음.", null);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(new ObjectMapper().writeValueAsString(responseDto));
        } else {
            try {
                // Access Token만 꺼내옴
//                String accessToken = header.substring(TOKEN_HEADER_PREFIX.length());

                String token = jwtProvider.resolveAccessToken(request);
                String loginId = jwtProvider.getMemberIdByAccessToken(token);

//                // === Access Token 검증 === //
//                JWTVerifier verifier = JWT.require(Algorithm.HMAC256(JWT_SECRET)).build();
//                DecodedJWT decodedJWT = verifier.verify(accessToken);
//
//                // === Access Token 내 Claim에서 Authorities 꺼내 Authentication 객체 생성 & SecurityContext에 저장 === //
//                List<String> strAuthorities = decodedJWT.getClaim("roles").asList(String.class);
//                List<SimpleGrantedAuthority> authorities = strAuthorities.stream().map(SimpleGrantedAuthority::new).toList();
//                String username = decodedJWT.getSubject();
//                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
//                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                if (loginId != null) {

                    Member member = memberRepository.findByMemberId(loginId).get();


                    PrincipalDetails principalDetails = new PrincipalDetails(member);
                    Authentication authentication = new UsernamePasswordAuthenticationToken(
                            loginId,
                            null, // 패스워드는 모르므로 null
                            principalDetails.getAuthorities()
                    );

                    SecurityContextHolder.getContext().setAuthentication(authentication);

                    // 인증 처리 후 정상적으로 다음 Filter 수행
                    chain.doFilter(request, response);

                }
            } catch (TokenExpiredException exception) {
                responseDto = new BaseResponse<>(HttpStatus.UNAUTHORIZED.value(), "Access Token이 만료되었습니다.", null);

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(new ObjectMapper().writeValueAsString(responseDto));

            } catch (Exception e) {
                responseDto = new BaseResponse<>(HttpStatus.BAD_REQUEST.value(), "잘못된 Access Token입니다..", null);

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(new ObjectMapper().writeValueAsString(responseDto));
            }
        }


//        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
//        log.info("header : {}", header);
//        if (header == null || !header.startsWith("Bearer ")) {
//            chain.doFilter(request, response);
//            return;
//        }
//
//        String token = jwtProvider.resolveAccessToken(request);
//        String loginId = jwtProvider.getMemberIdByAccessToken(token);
//
//        log.info("token : {}, loginId : {}", token, loginId);
//
//        log.info("Authorization memberId : {}", loginId);
//
//        if (loginId != null) {
//
//            Member member = memberRepository.findByMemberId(loginId).get();
//
//
//            PrincipalDetails principalDetails = new PrincipalDetails(member);
//            Authentication authentication = new UsernamePasswordAuthenticationToken(
//                    loginId,
//                    null, // 패스워드는 모르므로 null
//                    principalDetails.getAuthorities()
//            );
//
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//        }
//
//        chain.doFilter(request, response);
    }

//    @Override
//    protected void onUnsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
//        response.getWriter().write(new ObjectMapper().writeValueAsString(new BaseResponse<>(HttpStatus.UNAUTHORIZED.value(), "not access",null)));
//    }
}
