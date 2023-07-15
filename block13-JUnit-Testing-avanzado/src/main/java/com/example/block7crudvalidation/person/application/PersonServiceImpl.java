package com.example.block7crudvalidation.person.application;

import com.example.block7crudvalidation.person.domain.Person;
import com.example.block7crudvalidation.person.infrastructure.dto.input.PersonInputDTO;
import com.example.block7crudvalidation.person.infrastructure.dto.output.PersonOutputDTO;
import com.example.block7crudvalidation.person.infrastructure.dto.output.PersonStudentDTO;
import com.example.block7crudvalidation.person.infrastructure.dto.output.PersonTeacherDTO;
import com.example.block7crudvalidation.person.infrastructure.repository.PersonRepository;
import com.example.block7crudvalidation.student.domain.Student;
import com.example.block7crudvalidation.student.infrastructure.dto.output.StudentOutputDTO;
import com.example.block7crudvalidation.teacher.domain.Teacher;
import com.example.block7crudvalidation.teacher.infrastructure.dto.output.TeacherOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public PersonOutputDTO addPerson(PersonInputDTO personInputDTO) {
        Person person = new Person(personInputDTO);
        personRepository.save(person);

        return new PersonOutputDTO(person);
    }

    public List<PersonOutputDTO> getPersons(String outputType) {
        List<PersonOutputDTO> listaPersonas = new ArrayList<>();
        personRepository.findAll().forEach(person -> {
            if ("full".equals(outputType)) {
                if (person.getStudent() != null) {
                    Student student = person.getStudent();
                    PersonStudentDTO dto = new PersonStudentDTO(person);
                    dto.setStudent(new StudentOutputDTO(student));
                    listaPersonas.add(dto);
                } else if (person.getTeacher() != null) {
                    Teacher teacher = person.getTeacher();
                    PersonTeacherDTO dto = new PersonTeacherDTO(person);
                    dto.setTeacher(new TeacherOutputDTO(teacher));
                    listaPersonas.add(dto);
                }
            } else {
                listaPersonas.add(new PersonOutputDTO(person));
            }
        });

        return listaPersonas;
    }

    @Override
    public PersonOutputDTO getPersonById(Integer id, String outputType) {
        Person person = personRepository.findById(id).orElseThrow(() -> new RuntimeException("No se ha encontrado ninguna persona con el id: " + id));


        if ("full".equals(outputType)) {
            if (person.getStudent() != null) {
                Student student = person.getStudent();
                PersonStudentDTO dto = new PersonStudentDTO(person);
                dto.setStudent(new StudentOutputDTO(student));
                return dto;
            } else if (person.getTeacher() != null) {
                Teacher teacher = person.getTeacher();
                PersonTeacherDTO dto = new PersonTeacherDTO(person);
                dto.setTeacher(new TeacherOutputDTO(teacher));
                return dto;
            }
        }
        return new PersonOutputDTO(person);
    }

    @Override
    public List<PersonOutputDTO> getPersonName(String name, String outputType) {
        List<PersonOutputDTO> listaNombres = new ArrayList<>();
        personRepository.findByName(name).forEach(person -> {
            if ("full".equals(outputType)) {
                if (person.getStudent() != null) {
                    Student student = person.getStudent();
                    PersonStudentDTO dto = new PersonStudentDTO(person);
                    dto.setStudent(new StudentOutputDTO(student));
                    listaNombres.add(dto);
                } else if (person.getTeacher() != null) {
                    Teacher teacher = person.getTeacher();
                    PersonTeacherDTO dto = new PersonTeacherDTO(person);
                    dto.setTeacher(new TeacherOutputDTO(teacher));
                    listaNombres.add(dto);
                }
            } else {
                listaNombres.add(new PersonOutputDTO(person));
            }
        });

        return listaNombres;
    }

    @Override
    public PersonOutputDTO updatePerson(Integer id, PersonInputDTO personInputDto) throws RuntimeException {
        Person persona = personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se ha encontrado ningún username con el id: " + id));
        persona.update(personInputDto);
        personRepository.save(persona);

        return new PersonOutputDTO(persona);
    }

    @Override
    public void deletePerson(Integer id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se ha encontrado ningún usuario con el id: " + id));
        personRepository.delete(person);
    }

    @Override
    public TeacherOutputDTO getTeacher(Integer id) {
        String url = "http://localhost:8081/teacher/" + id;

        try{
            ResponseEntity<TeacherOutputDTO> response = restTemplate.exchange(url, HttpMethod.GET, null, TeacherOutputDTO.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody();
            } else {
                return new TeacherOutputDTO();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
