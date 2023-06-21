package com.example.block5commandlinerunner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TerceraClase implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Soy la tercera clase");

        // Imprimir los valores pasados como parámetros
        for (String arg : args) {
            System.out.println("Valor del parámetro introducido: " + arg);
        }
    }
}

/*
La tercera clase implementa de nuevo la interfaz "CommandLineRunner",
lo que le permite tener un método "run" que se ejecutará cuando se inicie la aplicación.
En este caso mostramos el mensaje "Hola desde clase secundaria".
 */
