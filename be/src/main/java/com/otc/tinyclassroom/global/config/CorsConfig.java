package com.otc.tinyclassroom.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);

        /*
         * TODO : addAllowedOriginPattern 을 client 주소로만 접근 가능하게 변경
         * */
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.addExposedHeader("*");
        source.registerCorsConfiguration("/**", config);

//        CorsConfiguration apiConfig = new CorsConfiguration();
//        apiConfig.setAllowCredentials(true);
//        config.addAllowedOriginPattern("*");
//        apiConfig.addAllowedHeader("*");
//        apiConfig.addAllowedMethod("*");
//        source.registerCorsConfiguration("/api/**", apiConfig);

        return new CorsFilter(source);
    }
}

