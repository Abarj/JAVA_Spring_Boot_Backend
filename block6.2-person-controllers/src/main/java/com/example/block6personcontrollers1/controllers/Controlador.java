package com.example.block6personcontrollers1.controllers;

import com.example.block6personcontrollers1.domains.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/controlador")
public class Controlador {

    private final Persona personaBean1;
    private final Persona personaBean2;
    private final Persona personaBean3;

    @Autowired
    public Controlador(@Qualifier("bean1") Persona personaBean1,
                       @Qualifier("bean2") Persona personaBean2,
                       @Qualifier("bean3") Persona personaBean3) {
        this.personaBean1 = personaBean1;
        this.personaBean2 = personaBean2;
        this.personaBean3 = personaBean3;
    }

    @GetMapping("/bean/{bean}")
    public ResponseEntity<Persona> getBean(@PathVariable String bean) {
        switch (bean) {
            case "bean1":
                return ResponseEntity.ok(personaBean1);
            case "bean2":
                return ResponseEntity.ok(personaBean2);
            case "bean3":
                return ResponseEntity.ok(personaBean3);
            default:
                return ResponseEntity.notFound().build();
        }
    }
}
