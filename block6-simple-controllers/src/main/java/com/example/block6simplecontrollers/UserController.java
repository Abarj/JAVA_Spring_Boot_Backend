package com.example.block6simplecontrollers;

import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @GetMapping("/user/{nombre}")
    public String getUser(@PathVariable String nombre) {
        return "Hola " + nombre;
    }

    @PostMapping("/useradd")
    public Persona addUser(@RequestBody Persona persona) {
        persona.setEdad(persona.getEdad() + 1);
        return persona;
    }
}