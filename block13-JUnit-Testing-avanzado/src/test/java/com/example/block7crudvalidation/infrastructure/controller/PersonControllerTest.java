package com.example.block7crudvalidation.infrastructure.controller;

import com.example.block7crudvalidation.person.application.PersonService;
import com.example.block7crudvalidation.person.domain.Person;
import com.example.block7crudvalidation.person.infrastructure.repository.PersonRepository;
import com.example.block7crudvalidation.person.infrastructure.dto.input.PersonInputDTO;
import com.example.block7crudvalidation.person.infrastructure.dto.output.PersonOutputDTO;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class PersonControllerTest {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonService personService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @AfterEach
    void tearDown() {
        personRepository.deleteAll();
    }

    @Test
    void addPerson() throws Exception {
        // Prepare
        PersonInputDTO personInputDto = new PersonInputDTO();
        personInputDto.setUsername("Juanito33");
        personInputDto.setName("Juan");
        personInputDto.setPassword("12345");
        personInputDto.setPersonalEmail("juan33@email.com");
        personInputDto.setCompanyEmail("juan@emailcorporativo.com");
        personInputDto.setCity("Madrid");
        personInputDto.setImagenUrl("https://image.jpg");
        personInputDto.setActive(true);

        // Execute
        mockMvc.perform(post("/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(personInputDto))
        ).andExpect(status().isCreated());

        // Verify
        assertThat(personRepository.findAll().size()).isOne();
    }

    @Test
    void testDeletePerson() throws Exception {
        PersonInputDTO personInputDto = new PersonInputDTO();
        personInputDto.setUsername("Abarj");
        personInputDto.setName("Alvaro");
        personInputDto.setPassword("12345");
        personInputDto.setPersonalEmail("alvaro@email.com");
        personInputDto.setCompanyEmail("alvaro@emailcorporativo.com");
        personInputDto.setCity("Madrid");
        personInputDto.setImagenUrl("https://image.jpg");
        personInputDto.setActive(true);

        when(personService.addPerson(personInputDto));


        // When and Then
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/person/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        verify(personService, times(1)).deletePerson(eq(1));
    }



    /*
    @Test
    void testGetPersons() throws Exception {
        // Given
        List<PersonOutputDTO> personOutputDTOList = new ArrayList<>();
        PersonOutputDTO personOutputDTO1 = new PersonOutputDTO(1, "abarj", "12345", "Alvaro", "Apellido1", "alvaro@email.com", "alvaro@email.com", "Madrid", true, null, "https://image.jpg", null);
        PersonOutputDTO personOutputDTO2 = new PersonOutputDTO(2, "user2", "abcde", "Paula", "Apellido2", "paula@email.com", "paula@email.com", "Barcelona", true, null, "https://image2.jpg", null);
        personOutputDTOList.add(personOutputDTO1);
        personOutputDTOList.add(personOutputDTO2);

        doReturn(personOutputDTOList).when(personService).getPersons(anyString());

        // Execute and Verify
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/person")
                        .param("outputType", "simple"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].idPerson").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].username").value("abarj"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].password").value("12345"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Alvaro"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].surname").value("Apellido1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].companyEmail").value("alvaro@email.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].personalEmail").value("alvaro@email.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].city").value("Madrid"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].idPerson").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].username").value("user2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].password").value("abcde"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Paula"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].surname").value("Apellido2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].companyEmail").value("paula@email.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].personalEmail").value("paula@email.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].city").value("Barcelona"))
                .andDo(MockMvcResultHandlers.print());

        verify(personService, times(1)).getPersons(anyString());
    }

    @Test
    void testGetPersonId() throws Exception {
        // Given
        PersonOutputDTO personOutputDTO = new PersonOutputDTO(1, "abarj", "12345", "Alvaro", "Apellido1", "alvaro@email.com", "alvaro@email.com", "Madrid", true, null, "https://image.jpg", null);

        when(personService.getPersonById(anyInt(), anyString())).thenReturn(personOutputDTO);

        // When and Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/person/{id}", 1)
                        .param("outputType", "simple"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.idPerson").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("abarj"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password").value("12345"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Alvaro"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.surname").value("Apellido1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.companyEmail").value("alvaro@email.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.personalEmail").value("alvaro@email.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.city").value("Madrid"))
                .andDo(MockMvcResultHandlers.print());

        verify(personService, times(1)).getPersonById(anyInt(), anyString());
    }

    @Test
    void testUpdatePerson() throws Exception {
        // Given
        PersonInputDTO personInputDTO = new PersonInputDTO();
        personInputDTO.setUsername("john");
        personInputDTO.setPassword("newpassword");
        personInputDTO.setName("John");
        personInputDTO.setCompanyEmail("john@example.com");
        personInputDTO.setPersonalEmail("john@gmail.com");
        personInputDTO.setCity("New York");

        PersonOutputDTO personOutputDTO = new PersonOutputDTO();
        personOutputDTO.setIdPerson(1);
        personOutputDTO.setUsername("john");
        personOutputDTO.setPassword("newpassword");
        personOutputDTO.setName("John");
        personOutputDTO.setCompanyEmail("john@example.com");
        personOutputDTO.setPersonalEmail("john@gmail.com");
        personOutputDTO.setCity("New York");

        when(personService.updatePerson(anyInt(), any(PersonInputDTO.class))).thenReturn(personOutputDTO);

        // When and Then
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/person/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"john\",\"password\":\"newpassword\",\"name\":\"John\",\"companyEmail\":\"john@example.com\",\"personalEmail\":\"john@gmail.com\",\"city\":\"New York\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.idPerson").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("john"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password").value("newpassword"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.companyEmail").value("john@example.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.personalEmail").value("john@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.city").value("New York"))
                .andDo(MockMvcResultHandlers.print());

        verify(personService, times(1)).updatePerson(eq(1), any(PersonInputDTO.class));
    }


     */
}
