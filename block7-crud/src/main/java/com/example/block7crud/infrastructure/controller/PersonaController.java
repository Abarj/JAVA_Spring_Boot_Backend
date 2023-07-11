package com.example.block7crud.infrastructure.controller;

import com.example.block7crud.application.PersonaService;
import com.example.block7crud.domain.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/persona")
public class PersonaController {
    private final PersonaService personaService;

    @Autowired
    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @PostMapping
    public ResponseEntity<?> añadirPersona(@RequestBody Persona persona) {
        if (persona.getNombre() == null || persona.getEdad() == null || persona.getPoblacion() == null) {
            return ResponseEntity.badRequest().body("Hay campos vacíos");
        } else {
            Persona nuevaPersona = personaService.create(persona);
            return ResponseEntity.ok("Persona guardada con éxito: " + nuevaPersona.toString());
        }
    }

    @GetMapping
    public ResponseEntity<List<Persona>> mostrarTodasLasPersonas() {
        Iterable<Persona> personas = personaService.findAll();
        List<Persona> listaPersonas = new ArrayList<>();
        personas.forEach(listaPersonas::add);

        if (!listaPersonas.isEmpty()) {
            return ResponseEntity.ok(listaPersonas);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modificarPersona(@PathVariable Long id, @RequestBody Persona persona) {
        if (persona.getNombre() == null || persona.getEdad() == null || persona.getPoblacion() == null) {
            return ResponseEntity.badRequest().body("Hay campos vacíos");
        } else {
            Optional<Persona> personaExistente = personaService.findById(id);
            if (personaExistente.isPresent()) {
                Persona personaActualizada = personaExistente.get();
                personaActualizada.setNombre(persona.getNombre());
                personaActualizada.setEdad(persona.getEdad());
                personaActualizada.setPoblacion(persona.getPoblacion());

                Optional<Persona> personaModificada = personaService.update(id, personaActualizada);
                if (personaModificada.isPresent()) {
                    return ResponseEntity.ok(personaModificada.get());
                } else {
                    return ResponseEntity.notFound().build();
                }
            } else {
                return ResponseEntity.notFound().build();
            }
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarPersona(@PathVariable Long id) {
        String resultado = personaService.delete(id);
        if (resultado.equals("Persona no encontrada")) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(resultado);
        }
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> consultarPorId(@PathVariable Long id) {
        Optional<Persona> persona = personaService.findById(id);
        if (persona.isPresent()) {
            return ResponseEntity.ok(persona.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Optional<Persona>> consultarPorNombre(@PathVariable String nombre) {
        Optional<Persona> personas = personaService.findByName(nombre);
        if (!personas.isEmpty()) {
            return ResponseEntity.ok(personas);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
