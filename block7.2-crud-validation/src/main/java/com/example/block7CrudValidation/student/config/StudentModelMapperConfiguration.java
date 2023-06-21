package com.example.block7CrudValidation.student.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentModelMapperConfiguration {

    @Bean
    public ModelMapper StudentModelMapperConfigurationNew() {
        return new ModelMapper();
    }
}
