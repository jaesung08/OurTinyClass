package com.otc.tinyclassroom.global.config;

import java.util.Optional;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Auditing을 위한 JPA Config.
 */
@EnableJpaAuditing
@Configuration
public class JpaConfig {

    @Bean
    public AuditorAware<String> auditorAware() {
        // TODO: 스프링 시큐리티 인증, 인가 구현 시 변경
        return () -> Optional.of("jaeseon");
    }

}
