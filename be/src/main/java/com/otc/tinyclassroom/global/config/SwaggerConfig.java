package com.otc.tinyclassroom.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger Config .
 */
@OpenAPIDefinition(
    info = @Info(title = "우리들의 작은 교실",
        description = "우작교 API 명세서",
        contact = @Contact(
            name = "우리들의 작은교실"
        )
    )
)
@Configuration
public class SwaggerConfig {

}