package com.example.block7CrudValidation.estudiante_asignatura.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EstudianteAsignaturaMapperConfiguration {

    @Bean
    public ModelMapper estudianteAsignaturaModelMapperConfiguration() {
        return new ModelMapper();
    }
}
