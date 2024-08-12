package com.example.runnersclub.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "RunnerSshop API 명세서입니다.")
)
@Configuration
public class SwaggerConfig {
}
