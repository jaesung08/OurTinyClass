package com.otc.tinyclassroom.global.config;

import com.otc.tinyclassroom.global.security.refresh.RefreshTokenRepository;
import com.otc.tinyclassroom.global.security.jwt.JwtAuthenticationFilter;
import com.otc.tinyclassroom.global.security.jwt.JwtAuthorizationFilter;
import com.otc.tinyclassroom.member.entity.Role;
import com.otc.tinyclassroom.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;

    private final CorsConfig corsConfig;

    private final MemberRepository memberRepository;
    private final RefreshTokenRepository refreshTokenRepository;


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                // csrf 토큰 방어 해제.
                .csrf(AbstractHttpConfigurer::disable)
                // cors 허용
                .addFilter(corsConfig.corsFilter())
                // 서버를 Stateless 하게 유지.
                .sessionManagement((sessionManagement) -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .formLogin((formLogin) -> formLogin
                        .disable()
                )
                // httpBasic 인증 방식 해제.
                .httpBasic((httpBasic) -> httpBasic
                        .disable()
                )

                // jwt 인증 필터 추가.
                .addFilter(new JwtAuthenticationFilter(authenticationManager(), refreshTokenRepository))
                // jwt 인가 필터 추가.
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), memberRepository))
//                .addFilter(new JwtLogoutFilter())
                .authorizeHttpRequests((authorizerRequests) -> authorizerRequests
                                .requestMatchers(("/api/members/join")).permitAll()
                                .requestMatchers("/api/members/login").permitAll()
                                .requestMatchers("/api/members/currentMember").hasAuthority(Role.ROLE_ADMIN.name())
//                        .requestMatchers("/student/**").hasAuthority("ROLE_STUDENT")
//                        .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                                .anyRequest().permitAll()
                );

        return http.build();
    }
}
