package com.example.block7crudvalidation.person.infrastructure.controller;

import com.example.block7crudvalidation.person.application.PersonService;
import com.example.block7crudvalidation.person.infrastructure.dto.input.PersonInputDTO;
import com.example.block7crudvalidation.person.infrastructure.dto.output.PersonOutputDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonService personService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PersonOutputDTO addPerson(@Valid @RequestBody PersonInputDTO personInputDTO) {
        return personService.addPerson(personInputDTO);
    }

    @GetMapping
    public List<PersonOutputDTO> getPersons() {
        return personService.getPersons();
    }

    @GetMapping("/{id}")
    public PersonOutputDTO getPersonId(@PathVariable("id") Integer id, @PathVariable("outputType") String outputType) throws Exception {
        return personService.getPersonById(id);
    }

    @GetMapping("/name/{name}")
    public List<PersonOutputDTO> getPersonName(@PathVariable("name") String username) throws Exception {
        return personService.getPersonName(username);
    }

    @PutMapping("/{id}")
    public PersonOutputDTO updatePerson(@PathVariable("id") Integer id, @RequestBody PersonInputDTO personInputDTO) {
        return personService.updatePerson(id, personInputDTO);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable("id") Integer id) {
        personService.deletePerson(id);
    }
}