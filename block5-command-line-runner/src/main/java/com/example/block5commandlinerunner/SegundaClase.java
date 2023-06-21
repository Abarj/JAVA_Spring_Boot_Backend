package com.example.block5commandlinerunner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SegundaClase implements CommandLineRunner {

    @Override
    public void run(String... args) {
        System.out.println("Hola desde clase secundaria");
    }
}

/*
La segunda clase implementa la interfaz "CommandLineRunner",
lo que le permite tener un método "run" que se ejecutará cuando se inicie la aplicación.
En este caso mostramos el mensaje "Hola desde clase secundaria".
 */