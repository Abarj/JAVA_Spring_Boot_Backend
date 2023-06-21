package com.example.block6personcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/controlador1")
public class Controlador1 {
    private final ServicioPersona servicioPersona;
    private final ServicioCiudad servicioCiudad;

    @Autowired
    public Controlador1(ServicioPersona servicioPersona, ServicioCiudad servicioCiudad) {
        this.servicioPersona = servicioPersona;
        this.servicioCiudad = servicioCiudad;
    }

    @PostMapping("/addPersona")
    public Persona addPersona(
            @RequestHeader String nombre,
            @RequestHeader String poblacion,
            @RequestHeader int edad
    ) {
        return servicioPersona.crearPersona(nombre, poblacion, edad);
    }

    @PostMapping("/addCiudad")
    public void addCiudad(@RequestBody Ciudad ciudad) {
        servicioCiudad.addCiudad(ciudad);
    }
}
