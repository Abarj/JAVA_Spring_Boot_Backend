package com.example.block7crudvalidation.person.infrastructure.controller;

import com.example.block7crudvalidation.config.feign.TeacherFeign;
import com.example.block7crudvalidation.person.application.PersonService;
import com.example.block7crudvalidation.person.domain.DateParameters;
import com.example.block7crudvalidation.person.domain.Person;
import com.example.block7crudvalidation.person.infrastructure.dto.input.PersonInputDTO;
import com.example.block7crudvalidation.person.infrastructure.dto.output.PersonOutputDTO;
import com.example.block7crudvalidation.teacher.infrastructure.dto.output.TeacherOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonService personService;

    @Autowired
    TeacherFeign teacherFeign;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PersonOutputDTO addPerson(@Valid @RequestBody PersonInputDTO personInputDTO) {
        return personService.addPerson(personInputDTO);
    }

    @GetMapping
    public List<PersonOutputDTO> getPersons(@RequestParam(value = "outputType", defaultValue = "simple") String outputType) {
        return personService.getPersons(outputType);
    }

    @GetMapping("/{id}")
    public PersonOutputDTO getPersonId(@PathVariable("id") Integer id, @RequestParam(value = "outputType", defaultValue = "simple") String outputType) throws Exception {
        return personService.getPersonById(id, outputType);
    }

    @GetMapping("/name/{name}")
    public List<PersonOutputDTO> getPersonName(@PathVariable("name") String name, @RequestParam(value = "outputType", defaultValue = "simple") String outputType) throws Exception {
        return personService.getPersonName(name, outputType);
    }

    // RestTemplate a 8081
    @GetMapping("/teacher/{id}")
    public TeacherOutputDTO getTeacher(@PathVariable Integer id) {
        return personService.getTeacher(id);

    }

    // Feign a 8081
    @GetMapping("/teacher/feign/{id}")
    public TeacherOutputDTO getTeacherFeign(@PathVariable Integer id) {
        return teacherFeign.getTeacher(id);

    }

    @PutMapping("/{id}")
    public PersonOutputDTO updatePerson(@PathVariable("id") Integer id, @RequestBody PersonInputDTO personInputDTO) {
        return personService.updatePerson(id, personInputDTO);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable("id") Integer id) {
        personService.deletePerson(id);
    }

    @GetMapping("/fields")
    public List<Person> searchByFields(@RequestParam(name = "username", required = false) String username,
                                       @RequestParam(name = "name", required = false) String name,
                                       @RequestParam(name = "surname", required = false) String surname,
                                       @RequestParam(name = "createdDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date createdDate,
                                       @RequestParam(required=false, defaultValue = DateParameters.GREATER_THAN) String dateCondition,
                                       @RequestParam(name = "orderBy", defaultValue = "username", required = false) String orderBy) {

        List<Person> people = personService.getByFields(username, name, surname, createdDate, orderBy, dateCondition);
        return people.stream().toList();
    }

    @GetMapping("/paginated")
    public Page<Person> searchAllWithPagination(@RequestParam(value = "offset", defaultValue = "1") int offset,
                                                @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        Page<Person> people = personService.getPeoplePagination(offset, pageSize);

        return people;
    }
}