package com.example.block6personcontrollers1.controllers;

import com.example.block6personcontrollers1.domains.Ciudad;
import com.example.block6personcontrollers1.domains.Persona;
import com.example.block6personcontrollers1.application.CiudadService;
import com.example.block6personcontrollers1.application.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/controlador1")
public class Controlador1 {

    private final PersonaService personaService;
    private final CiudadService ciudadService;

    @Autowired
    public Controlador1(PersonaService personaService, CiudadService ciudadService) {
        this.personaService = personaService;
        this.ciudadService = ciudadService;
    }

    @PostMapping("/addPersona")
    public ResponseEntity<Persona> addPersona(
            @RequestHeader("nombre") String nombre,
            @RequestHeader("poblacion") String poblacion,
            @RequestHeader("edad") int edad
    ) {
        Persona persona = personaService.crearPersona(nombre, poblacion, edad);
        return ResponseEntity.status(HttpStatus.CREATED).body(persona);
    }

    @PostMapping("/addCiudad")
    public ResponseEntity<Void> addCiudad(@RequestBody Ciudad ciudad) {
        ciudadService.agregarCiudad(ciudad);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
