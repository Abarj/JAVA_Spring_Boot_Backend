package com.example.block13mongodb.person.infrastructure.controller;

import com.example.block13mongodb.person.application.PersonDAL;
import com.example.block13mongodb.person.domain.Person;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonDAL personDAL;

    @PostMapping
    public Person addPerson(@RequestBody Person person) {
        return new Person(personDAL.addPerson(new Person(person)));
    }

    @GetMapping
    public List<Person> getAllPersonsPaginated(
            @RequestParam(defaultValue = "1") int pageNumber,
            @RequestParam(defaultValue = "3") int pageSize) {
        return personDAL.getAllPersonPaginated(pageNumber, pageSize);
    }

    @GetMapping("/{id}")
    public Person findById(@PathVariable("id") String id) {
        return personDAL.findById(id);
    }

    @GetMapping("/name/{name}")
    public List<Person> getPersonaName(@PathVariable("name") String name) {
        return personDAL.findByName(name).stream().map(Person::new).collect(Collectors.toList());
    }

    @PutMapping("{id}")
    public Person updatePersona(@PathVariable("id") String id, @RequestBody Person person) {
        return new Person(personDAL.updatePerson(id, new Person(person)));
    }

    @DeleteMapping
    public void deletePersona(@RequestBody Person person) {
        personDAL.deletePerson(new Person(person));
    }
}
