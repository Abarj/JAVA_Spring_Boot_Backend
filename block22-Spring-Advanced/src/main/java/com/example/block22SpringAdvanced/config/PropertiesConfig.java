package com.example.block22SpringAdvanced.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({
        @PropertySource(value = "classpath:config/api_currency.properties"),
        @PropertySource(value = "classpath:config/redis.properties"),
        @PropertySource(value = "classpath:config/client_security.properties"),
        @PropertySource(value = "classpath:sonar-project.properties")
})
public class PropertiesConfig {
}
