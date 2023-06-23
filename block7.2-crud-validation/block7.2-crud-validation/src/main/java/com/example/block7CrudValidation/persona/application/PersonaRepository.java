package com.example.block7CrudValidation.persona.application;

import com.example.block7CrudValidation.persona.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer> {
    List<Persona> findByName(String name);
}
