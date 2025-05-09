package com.pending.rtc.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Value("${git.commit.id.abbrev:unknown}")
    private String gitCommitId;

    private Info info(){
        return new Info()
                .title("webRTC")
                .version(gitCommitId)
                .description("webRTC를 이용한 프로젝트 - API 명세서");
    }

    // JWT 토큰 사용을 위한 Swagger 자체 모듈
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("access-token", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")))
                .addSecurityItem(new SecurityRequirement().addList("access-token"))
                .info(info());
    }
}
