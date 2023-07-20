package com.example.block7crudvalidation.infrastructure.controller;

import com.example.block7crudvalidation.person.application.PersonService;
import com.example.block7crudvalidation.person.domain.Person;
import com.example.block7crudvalidation.person.infrastructure.repository.PersonRepository;
import com.example.block7crudvalidation.student.application.StudentService;
import com.example.block7crudvalidation.student.infrastructure.dto.input.StudentInputDTO;
import com.example.block7crudvalidation.student.infrastructure.dto.output.StudentOutputDTO;
import com.example.block7crudvalidation.student.infrastructure.repository.StudentRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private StudentService studentService;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonService personService;

    @Autowired
    private StudentRepository studentRepository;

    @AfterEach
    void tearDown() {
        studentRepository.deleteAll();
    }

    private StudentInputDTO setUpInput() {
        StudentInputDTO studentInputDTO = new StudentInputDTO();
        Person person = new Person();
        personRepository.save(person);

        studentInputDTO.setIdPerson(1);
        studentInputDTO.setNumHoursWeek(20);
        studentInputDTO.setComments("Comentarios");
        studentInputDTO.setBranch("Rama");

        return studentInputDTO;
    }
    private StudentInputDTO setUpInput2() {
        StudentInputDTO studentInputDTO = new StudentInputDTO();
        Person person = new Person();
        person.setIdPerson(2);
        personRepository.save(person);

        studentInputDTO.setIdPerson(2);
        studentInputDTO.setNumHoursWeek(30);
        studentInputDTO.setComments("Comentarios2");
        studentInputDTO.setBranch("Rama2");

        return studentInputDTO;
    }

    @Test
    void addStudent() throws Exception {
        // Prepare
        StudentInputDTO studentInputDTO = setUpInput();

        // Execute
        mockMvc.perform(post("/student")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(studentInputDTO))
        ).andExpect(status().isOk());

        // Verify
        assertThat(studentRepository.findAll().size()).isOne();
    }

    @Test
    void getAllStudents() throws Exception {
        // Prepare
        StudentOutputDTO student1 = studentService.addStudent(setUpInput());
        StudentOutputDTO student2 = studentService.addStudent(setUpInput2());

        // Execute
        MvcResult mvcResult = mockMvc.perform(get("/student")
                .param("outputType", "simple")
        ).andExpect(status().isOk()).andReturn();

        // Verify
        List<StudentOutputDTO> actualStudents = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {});
        List<StudentOutputDTO> expectedStudents = studentService.getAllStudents("simple");
        assertThat(actualStudents).usingRecursiveComparison().isEqualTo(expectedStudents);
    }

    @Test
    void getStudentId() throws Exception {
        // Prepare
        StudentOutputDTO student = studentService.addStudent(setUpInput());

        // Execute
        MvcResult mvcResult = mockMvc.perform(get("/student/{id}", student.getIdStudent())
                .param("outputType", "simple")
        ).andExpect(status().isOk()).andReturn();

        // Verify
        StudentOutputDTO actualStudent = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), StudentOutputDTO.class);
        StudentOutputDTO expectedStudent = studentService.getStudentById(actualStudent.getIdStudent());
        assertThat(actualStudent).isEqualToComparingFieldByField(expectedStudent);
    }

    @Test
    void updateStudent() throws Exception {
        // Prepare
        StudentOutputDTO student = studentService.addStudent(setUpInput());

        StudentInputDTO updatedStudentInputDTO = new StudentInputDTO();
        updatedStudentInputDTO.setIdPerson(1);
        updatedStudentInputDTO.setNumHoursWeek(35);
        updatedStudentInputDTO.setComments("Comentarios actualizados");
        updatedStudentInputDTO.setBranch("Rama actualizada");

        // Execute
        MvcResult mvcResult = mockMvc.perform(put("/student/{id}", student.getIdStudent())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedStudentInputDTO))
        ).andExpect(status().isOk()).andReturn();

        // Verify
        StudentOutputDTO actualStudent = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), StudentOutputDTO.class);
        StudentOutputDTO expectedStudent = studentService.getStudentById(actualStudent.getIdStudent());
        assertThat(actualStudent).isEqualToComparingFieldByField(expectedStudent);
    }

    @Test
    void deleteStudent() throws Exception {
        // Prepare
        StudentOutputDTO student = studentService.addStudent(setUpInput());

        // Execute
        mockMvc.perform(delete("/student/{id}", student.getIdStudent()))
                .andExpect(status().isOk());

        // Verify
        assertThat(studentService.getAllStudents("simple")).isEmpty();
    }
}

