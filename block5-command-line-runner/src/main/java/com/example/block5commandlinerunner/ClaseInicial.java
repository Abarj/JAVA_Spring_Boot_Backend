package com.example.block5commandlinerunner;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class ClaseInicial {

    @PostConstruct
    public void postConstruct() {
        System.out.println("Hola desde clase inicial");
    }
}

/*
La primera clase tiene un método con @PostConstruct
que se ejecutará automáticamente después de la construcción del objeto.
En este caso mostramos el mensaje "Hola desde clase inicial".
 */
