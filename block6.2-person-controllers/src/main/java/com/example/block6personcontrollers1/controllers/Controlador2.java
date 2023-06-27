package com.example.block6personcontrollers1.controllers;

import com.example.block6personcontrollers1.domains.Persona;
import com.example.block6personcontrollers1.domains.Ciudad;
import com.example.block6personcontrollers1.application.PersonaService;
import com.example.block6personcontrollers1.application.CiudadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/controlador2")
public class Controlador2 {

    private final PersonaService personaService;
    private final CiudadService ciudadService;

    @Autowired
    public Controlador2(PersonaService personaService, CiudadService ciudadService) {
        this.personaService = personaService;
        this.ciudadService = ciudadService;
    }

    @GetMapping("/getPersona")
    public ResponseEntity<Persona> getPersona() {
        Persona persona = personaService.getPersona();
        if (persona != null) {
            persona.setEdad(persona.getEdad() * 2);
            return ResponseEntity.ok(persona);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getCiudades")
    public ResponseEntity<List<Ciudad>> getCiudades() {
        List<Ciudad> ciudades = ciudadService.obtenerCiudades();
        return ResponseEntity.ok(ciudades);
    }
}
