package com.example.block7CrudValidation.persona.infrastructure.controllers;

import com.example.block7CrudValidation.exceptions.CreateUserException;
import com.example.block7CrudValidation.persona.application.PersonaService;
import com.example.block7CrudValidation.persona.domain.Persona;
import com.example.block7CrudValidation.persona.infrastructure.dto.PersonDTO;
import com.example.block7CrudValidation.persona.infrastructure.dto.StudentPersonDTO;
import com.example.block7CrudValidation.persona.infrastructure.dto.TeacherPersonDTO;
import com.example.block7CrudValidation.profesor.application.ProfesorService;
import com.example.block7CrudValidation.profesor.domain.Profesor;
import com.example.block7CrudValidation.profesor.infrastructure.dto.DtoProfesor;
import com.example.block7CrudValidation.profesor.infrastructure.dto.ProfesorOutputDto;
import com.example.block7CrudValidation.student.application.StudentService;
import com.example.block7CrudValidation.student.domain.Student;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/persona")
public class PersonaController {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    PersonaService personaService;
    @Autowired
    StudentService studentService;
    @Autowired
    ProfesorService profesorService;

    @PostMapping
    public ResponseEntity<Persona> addPerson(@RequestBody Persona person) throws Exception {
        if (person.validarDatos()) {
            Timestamp createdDate = person.getCreated_date() != null ? new Timestamp(person.getCreated_date().getTime()) : null;
            person.setCreated_date(createdDate);

            Timestamp terminationDate = person.getTermination_date() != null ? new Timestamp(person.getTermination_date().getTime()) : null;
            person.setTermination_date(terminationDate);

            personaService.createPerson(person);
            return ResponseEntity.ok(person);
        } else {
            throw new CreateUserException("No ha podido crearse la persona");
        }
    }


    @GetMapping
    public ResponseEntity<List<PersonDTO>> getAll(@RequestParam(name = "outputType", defaultValue = "simple") String outputType) {

        // Obtener la lista de personas, estudiantes y profesores
        List<Persona> personas = personaService.getAll();
        List<PersonDTO> personDTOs = new ArrayList<>();
        List<Student> listaEstudiantes = studentService.readEveryStudent();
        List<Profesor> listaProfesores = profesorService.readEveryTeacher();

        for (Persona persona : personas) {
            PersonDTO personDTO = new PersonDTO();
            personDTO.getPersonInfo(persona);

            if (outputType.equals("full")) {
                if (studentService.filterByID(listaEstudiantes, persona.getId_person())) {
                    StudentPersonDTO studentPersonDTO = new StudentPersonDTO();
                    Student student = studentService.getById(listaEstudiantes, persona.getId_person());
                    studentPersonDTO.getStudentPersonInfo(student);
                    personDTO = modelMapper.map(studentPersonDTO, PersonDTO.class);
                } else if (profesorService.filterByID(listaProfesores, persona.getId_person())) {
                    TeacherPersonDTO teacherPersonDTO = new TeacherPersonDTO();
                    Profesor profesor = profesorService.getByID(listaProfesores, persona.getId_person());
                    teacherPersonDTO.getTeacherPersonInfo(profesor);
                    personDTO = modelMapper.map(teacherPersonDTO, PersonDTO.class);
                }
            }
            personDTOs.add(personDTO);
        }
        return ResponseEntity.ok().body(personDTOs);
    }



    @GetMapping(value="/id/{id}")
    public ResponseEntity<PersonDTO> getById(@PathVariable(name="id") int id, @RequestParam(name="outputType", defaultValue="simple") String type) throws CreateUserException {
        // Obtener la lista de estudiantes y profesores
        List<Student> listaEstudiantes = studentService.readEveryStudent();
        List<Profesor> listaProfesores = profesorService.readEveryTeacher();

        // Validar el parámetro outputType para determinar el tipo de respuesta
        if (type.equals("full")) {
            // Verificar si la ID corresponde a un estudiante
            if (studentService.filterByID(listaEstudiantes, id)) {
                // Crear un objeto StudentPersonDTO y obtener el estudiante correspondiente
                StudentPersonDTO studentPersonDTO = new StudentPersonDTO();
                Student student = studentService.getById(listaEstudiantes, id);
                // Obtener la información del estudiante en el objeto StudentPersonDTO
                studentPersonDTO.getStudentPersonInfo(student);
                // Mapear el objeto StudentPersonDTO a DtoPersona usando el ModelMapper
                PersonDTO personaResponse = modelMapper.map(studentPersonDTO, PersonDTO.class);
                // Devolver la respuesta con el objeto DtoPersona
                return ResponseEntity.ok().body(personaResponse);
            } else if (profesorService.filterByID(listaProfesores, id)) {
                // Verificar si la ID corresponde a un profesor
                // Crear un objeto TeacherPersonDTO y obtener el profesor correspondiente
                TeacherPersonDTO teacherPersonDTO = new TeacherPersonDTO();
                Profesor profesor = profesorService.getByID(listaProfesores, id);
                // Obtener la información del profesor en el objeto TeacherPersonDTO
                teacherPersonDTO.getTeacherPersonInfo(profesor);
                // Mapear el objeto TeacherPersonDTO a DtoPersona usando el ModelMapper
                PersonDTO personaResponse = modelMapper.map(teacherPersonDTO, PersonDTO.class);
                // Devolver la respuesta con el objeto DtoPersona
                return ResponseEntity.ok().body(personaResponse);
            } else {
                // Si la ID no corresponde a un estudiante ni a un profesor, obtener la información de la persona
                PersonDTO personDTO = new PersonDTO();
                personDTO.getPersonInfo(personaService.getById(id));
                // Mapear el objeto PersonDTO a DtoPersona usando el ModelMapper
                PersonDTO personaResponse = modelMapper.map(personDTO, PersonDTO.class);
                // Devolver la respuesta con el objeto DtoPersona
                return ResponseEntity.ok().body(personaResponse);
            }
        } else {
            // Si el outputType no es "full", obtener la información básica de la persona
            PersonDTO personDTO = new PersonDTO();
            personDTO.getPersonInfo(personaService.getById(id));
            // Mapear el objeto PersonDTO a DtoPersona usando el ModelMapper
            PersonDTO personaResponse = modelMapper.map(personDTO, PersonDTO.class);
            // Devolver la respuesta con el objeto DtoPersona
            return ResponseEntity.ok().body(personaResponse);
        }
    }

    @GetMapping(value = "/nombre/{name}")
    public ResponseEntity<List<PersonDTO>> getByName(@PathVariable(name = "name") String name, @RequestParam(name = "outputType", defaultValue = "simple") String outputType) {

        // Obtener la lista de estudiantes y profesores
        List<Persona> personas = personaService.getByName(name);
        List<PersonDTO> personDTOs = new ArrayList<>();
        List<Student> listaEstudiantes = studentService.readEveryStudent();
        List<Profesor> listaProfesores = profesorService.readEveryTeacher();

        for (Persona persona : personas) {
            PersonDTO personDTO = new PersonDTO();
            personDTO.getPersonInfo(persona);

            if (outputType.equals("full")) {
                if (studentService.filterByID(listaEstudiantes, persona.getId_person())) {
                    StudentPersonDTO studentPersonDTO = new StudentPersonDTO();
                    Student student = studentService.getById(listaEstudiantes, persona.getId_person());
                    studentPersonDTO.getStudentPersonInfo(student);
                    personDTO = modelMapper.map(studentPersonDTO, PersonDTO.class);
                } else if (profesorService.filterByID(listaProfesores, persona.getId_person())) {
                    TeacherPersonDTO teacherPersonDTO = new TeacherPersonDTO();
                    Profesor profesor = profesorService.getByID(listaProfesores, persona.getId_person());
                    teacherPersonDTO.getTeacherPersonInfo(profesor);
                    personDTO = modelMapper.map(teacherPersonDTO, PersonDTO.class);
                }
            }

            personDTOs.add(personDTO);
        }

        return ResponseEntity.ok().body(personDTOs);
    }


    @PutMapping(value="/{id}")
    public ResponseEntity<PersonDTO> updatePerson(@PathVariable(name="id") Integer id, @RequestBody Persona person) {
        person.setId_person(id); // Establecer el ID en el objeto Persona
        Persona updatedPerson = personaService.updatePerson(person);
        PersonDTO personResponse = modelMapper.map(updatedPerson, PersonDTO.class);

        return ResponseEntity.ok().body(personResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Integer id) throws CreateUserException {
        Persona existingPerson = personaService.getById(id);
        if (existingPerson != null) {
            personaService.deletePerson(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Autowired
    private RestTemplate restTemplate;

    public PersonaController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/profesor/{id}")
    public ProfesorOutputDto getProfesor(@PathVariable int id) {
        String url = "http://localhost:8081/profesores/" + id;

        try {
            ResponseEntity<ProfesorOutputDto> response = restTemplate.exchange(url, HttpMethod.GET, null, ProfesorOutputDto.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody();
            } else {
                return new ProfesorOutputDto();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ProfesorOutputDto();
        }
    }


}