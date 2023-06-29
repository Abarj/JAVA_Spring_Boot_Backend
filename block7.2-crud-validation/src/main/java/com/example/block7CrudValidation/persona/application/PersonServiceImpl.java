package com.example.block7CrudValidation.persona.application;

import com.example.block7CrudValidation.persona.domain.Persona;
import com.example.block7CrudValidation.exceptions.CreateUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonaService {

    @Autowired
    PersonaRepository personRepository;

    @Override
    public Persona createPerson(Persona person) {
        personRepository.save(person);
        return person;
    }

    @Override
    public Persona updatePerson(Persona person) {
        personRepository.save(person);
        return person;
    }

    @Override
    public List<Persona> getByName(String name) {
        return personRepository.findByName(name);
    }

    @Override
    public Persona getById(Integer id) throws CreateUserException {
        return personRepository.findById(id).orElseThrow(() -> new CreateUserException("No hay nadie con este id"));

    }

    @Override
    public List<Persona> getAll() {
        return personRepository.findAll();
    }

    @Override
    public void deletePerson(Integer id) {
        personRepository.deleteById(id);
    }
}