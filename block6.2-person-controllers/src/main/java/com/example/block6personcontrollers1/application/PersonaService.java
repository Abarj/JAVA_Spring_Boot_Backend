package com.example.block6personcontrollers1.application;

import org.springframework.stereotype.Service;
import com.example.block6personcontrollers1.domains.Persona;

@Service
public class PersonaService {

    private Persona persona;

    public Persona crearPersona(String nombre, String poblacion, int edad) {
        persona = new Persona(nombre, poblacion, edad);
        return persona;
    }

    public Persona getPersona() {
        return persona;
    }
}
