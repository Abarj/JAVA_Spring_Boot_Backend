package com.example.block7CrudValidation.profesor.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProfesorModelMapperConfiguration {

    @Bean
    public ModelMapper profesorModelMapper() {
        return new ModelMapper();
    }
}

