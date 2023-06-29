package com.example.block7CrudValidation.persona.application;

import com.example.block7CrudValidation.exceptions.CreateUserException;
import com.example.block7CrudValidation.persona.domain.Persona;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PersonaService {
    Persona createPerson(Persona person);
    Persona updatePerson(Persona person);
    List<Persona> getByName(String name);
    Persona getById(Integer id) throws CreateUserException, CreateUserException;
    List<Persona> getAll();
    void deletePerson(Integer id);
}