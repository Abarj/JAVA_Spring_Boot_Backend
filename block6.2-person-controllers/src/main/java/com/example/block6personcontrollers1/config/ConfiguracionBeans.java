package com.example.block6personcontrollers1.config;

import com.example.block6personcontrollers1.domains.Persona;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfiguracionBeans {

    @Bean
    @Qualifier("bean1")
    public Persona personaBean1() {
        return new Persona("David", "Zaragoza", 26);
    }

    @Bean
    @Qualifier("bean2")
    public Persona personaBean2() {
        return new Persona("Patricia", "Valencia", 31);
    }

    @Bean
    @Qualifier("bean3")
    public Persona personaBean3() {
        return new Persona("Claudia", "Logroño", 42);
    }
}