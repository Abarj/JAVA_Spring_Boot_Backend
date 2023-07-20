package com.example.block7crudvalidation.infrastructure.controller;

import com.example.block7crudvalidation.person.domain.Person;
import com.example.block7crudvalidation.person.infrastructure.repository.PersonRepository;
import com.example.block7crudvalidation.teacher.application.TeacherService;
import com.example.block7crudvalidation.teacher.infrastructure.dto.input.TeacherInputDTO;
import com.example.block7crudvalidation.teacher.infrastructure.dto.output.TeacherOutputDTO;
import com.example.block7crudvalidation.teacher.infrastructure.repository.TeacherRepository;
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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class TeacherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private PersonRepository personRepository;

    @AfterEach
    void tearDown() {
        teacherRepository.deleteAll();
    }

    private TeacherInputDTO setUpInput() {
        TeacherInputDTO teacherInputDTO = new TeacherInputDTO();
        Person person = new Person();
        personRepository.save(person);

        teacherInputDTO.setIdPerson(1);
        teacherInputDTO.setComments("Comentarios");
        teacherInputDTO.setBranch("Rama");

        return teacherInputDTO;
    }

    private TeacherInputDTO setUpInput2() {
        TeacherInputDTO teacherInputDTO = new TeacherInputDTO();
        Person person = new Person();
        person.setIdPerson(2);
        personRepository.save(person);

        teacherInputDTO.setIdPerson(2);
        teacherInputDTO.setComments("Comentarios2");
        teacherInputDTO.setBranch("Rama2");

        return teacherInputDTO;
    }

    @Test
    void addTeacher() throws Exception {
        // Prepare
        TeacherInputDTO teacherInputDTO = setUpInput();

        // Execute
        MvcResult mvcResult = mockMvc.perform(post("/teacher")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(teacherInputDTO))
        ).andExpect(status().isOk()).andReturn();

        // Verify
        TeacherOutputDTO actualTeacher = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), TeacherOutputDTO.class);
        TeacherOutputDTO expectedTeacher = teacherService.getTeacherId(actualTeacher.getIdTeacher(), "simple");
        assertThat(actualTeacher).isEqualToComparingFieldByField(expectedTeacher);
    }

    @Test
    void getAllTeachers() throws Exception {
        // Prepare
        TeacherOutputDTO teacher1 = teacherService.addTeacher(setUpInput());
        TeacherOutputDTO teacher2 = teacherService.addTeacher(setUpInput2());

        // Execute
        MvcResult mvcResult = mockMvc.perform(get("/teacher")
                .param("outputType", "simple")
        ).andExpect(status().isOk()).andReturn();

        // Verify
        List<TeacherOutputDTO> actualTeachers = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {});
        List<TeacherOutputDTO> expectedTeachers = teacherService.getAllTeachers("simple");
        assertThat(actualTeachers).usingRecursiveComparison().isEqualTo(expectedTeachers);
    }

    @Test
    void getTeacherId() throws Exception {
        // Prepare
        TeacherOutputDTO teacher = teacherService.addTeacher(setUpInput());

        // Execute
        MvcResult mvcResult = mockMvc.perform(get("/teacher/{id}", teacher.getIdTeacher())
                .param("outputType", "simple")
        ).andExpect(status().isOk()).andReturn();

        // Verify
        TeacherOutputDTO actualTeacher = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), TeacherOutputDTO.class);
        TeacherOutputDTO expectedTeacher = teacherService.getTeacherId(actualTeacher.getIdTeacher(), "simple");
        assertThat(actualTeacher).isEqualToComparingFieldByField(expectedTeacher);
    }

    @Test
    void updateTeacher() throws Exception {
        // Prepare
        TeacherOutputDTO teacher = teacherService.addTeacher(setUpInput());

        TeacherInputDTO updatedTeacherInputDTO = new TeacherInputDTO();
        updatedTeacherInputDTO.setIdPerson(1);
        updatedTeacherInputDTO.setComments("Comentarios actualizados");
        updatedTeacherInputDTO.setBranch("Rama actualizada");

        // Execute
        MvcResult mvcResult = mockMvc.perform(put("/teacher/{id}", teacher.getIdTeacher())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedTeacherInputDTO))
        ).andExpect(status().isOk()).andReturn();

        // Verify
        TeacherOutputDTO actualTeacher = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), TeacherOutputDTO.class);
        TeacherOutputDTO expectedTeacher = teacherService.getTeacherId(actualTeacher.getIdTeacher(), "simple");
        assertThat(actualTeacher).isEqualToComparingFieldByField(expectedTeacher);
    }

    @Test
    void deleteTeacher() throws Exception {
        // Prepare
        TeacherOutputDTO teacher = teacherService.addTeacher(setUpInput());

        // Execute
        mockMvc.perform(delete("/teacher/{id}", teacher.getIdTeacher()))
                .andExpect(status().isOk());

        // Verify
        assertThat(teacherService.getAllTeachers("simple")).isEmpty();
    }
}
