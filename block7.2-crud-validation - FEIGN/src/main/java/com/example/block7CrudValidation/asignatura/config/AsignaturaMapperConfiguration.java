package com.example.block7CrudValidation.asignatura.config;

import com.example.block7CrudValidation.asignatura.domain.Asignatura;
import com.example.block7CrudValidation.asignatura.infrastructure.dto.DtoAsignatura;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AsignaturaMapperConfiguration {

    @Bean
    public ModelMapper estudianteAsignaturaModelMapperConfiguration() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.createTypeMap(DtoAsignatura.class, Asignatura.class)
                .addMapping(DtoAsignatura::getStudent, Asignatura::setStudent)
                .addMapping(DtoAsignatura::getSubject, Asignatura::setSubject)
                .addMapping(DtoAsignatura::getInitial_date, Asignatura::setInitial_date)
                .addMapping(DtoAsignatura::getFinish_date, Asignatura::setFinish_date);

        return modelMapper;
    }
}
