package com.example.block17.SpringBatchApplication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class ApplicationConfig {

    // Configuración de la conexión a la BD directamente desde una clase de configuración de Spring
    // Normalmente se configura en application.properties pero al trabajar con Spring Batch es recomendable hacerlo a través de una clase de configuración
    // Esto es debido a que cuando trabajamos con Spring Batch constantemente hay que estar agregando configuraciones
    @Bean
    public DataSource dataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/block172batchTest");
        dataSource.setUsername("root");
        dataSource.setPassword("root");

        return dataSource;
    }
}
