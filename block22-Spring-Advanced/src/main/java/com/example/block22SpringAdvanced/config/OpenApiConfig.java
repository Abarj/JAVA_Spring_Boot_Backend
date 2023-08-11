package com.example.block22SpringAdvanced.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(title = "block22 Spring Advanced", version = "1.0", description = "Documentation for endpoints in Block22-SpringAdvanced")
)
public class OpenApiConfig {
}
