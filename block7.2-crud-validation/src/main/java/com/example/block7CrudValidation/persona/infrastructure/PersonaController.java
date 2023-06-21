package com.example.block7CrudValidation.persona.infrastructure;

import com.example.block7CrudValidation.exceptions.CreateUserException;
import com.example.block7CrudValidation.persona.application.PersonaService;
import com.example.block7CrudValidation.persona.domain.Persona;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/persona")
public class PersonaController {

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    PersonaService personaService;

    @PostMapping("/crearPersona")
    public ResponseEntity<Persona> addPerson(@RequestBody Persona person) throws Exception {
        if (person.validarDatos()) {
            personaService.createPerson(person);
            return ResponseEntity.ok(person);
        } else {
            throw new CreateUserException("No ha podido crearse la persona");
        }
    }


    @PutMapping(value="/modificarPersona")
    public ResponseEntity<DtoPersona> updatePerson(@RequestBody DtoPersona dtoPerson) {
        Persona personaRequest = modelMapper.map(dtoPerson, Persona.class);
        Persona person = personaService.updatePerson(personaRequest);
        DtoPersona personResponse = modelMapper.map(person, DtoPersona.class);

        return ResponseEntity.ok().body(personResponse);
    }

    @GetMapping(value="/nombre/{name}")
    public List<DtoPersona> getByName(@PathVariable(name="name") String name) {
        return personaService.getByName(name).stream().map(person -> modelMapper.map(person, DtoPersona.class)).collect(Collectors.toList());
    }

    @GetMapping(value="/persona/{id}")
    public ResponseEntity<DtoPersona> getById(@PathVariable(name="id") Integer id) throws CreateUserException {
        Persona person = personaService.getById(id);
        DtoPersona personaResponse = modelMapper.map(person, DtoPersona.class);

        return ResponseEntity.ok().body(personaResponse);
    }

    @GetMapping(value="mostrarTodos")
    public List<DtoPersona> getAll() {
        return personaService.getAll().stream().map(person -> modelMapper.map(person, DtoPersona.class)).collect(Collectors.toList());
    }
}