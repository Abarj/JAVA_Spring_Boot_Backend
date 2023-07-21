package com.example.block7crud.application;

import com.example.block7crud.domain.Persona;
import java.util.Optional;

public interface PersonaService {
    Iterable<Persona> findAll();
    Optional<Persona> findById(Long id);
    Optional<Persona> findByName(String name);
    Persona create(Persona newPerson);
    Optional<Persona> update(Long id, Persona newPerson);
    String delete(Long id);
}