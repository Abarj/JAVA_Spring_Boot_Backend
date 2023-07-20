package com.example.block7crudvalidation.infrastructure.controller;

import com.example.block7crudvalidation.person.application.PersonService;
import com.example.block7crudvalidation.person.domain.Person;
import com.example.block7crudvalidation.person.infrastructure.dto.input.PersonInputDTO;
import com.example.block7crudvalidation.person.infrastructure.dto.output.PersonOutputDTO;
import com.example.block7crudvalidation.person.infrastructure.repository.PersonRepository;

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

    private Person setUp() {
        Person person = new Person();
        person.setIdPerson(1);
        person.setUsername("Abarj");
        person.setName("Alvaro");
        person.setPassword("12345");
        person.setPersonalEmail("alvaro@email.com");
        person.setCompanyEmail("alvaro@emailcorporativo.com");
        person.setCity("Madrid");
        person.setImageUrl("https://image.jpg");
        person.setActive(true);

        return personRepository.save(person);
    }

    @Test
    void addPerson() throws Exception {
        // Prepare
        PersonInputDTO personInputDto = new PersonInputDTO();
        personInputDto.setUsername("Abarj");
        personInputDto.setName("Alvaro");
        personInputDto.setPassword("12345");
        personInputDto.setPersonalEmail("alvaro@email.com");
        personInputDto.setCompanyEmail("alvaro@emailcorporativo.com");
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
    void testGetPersons() throws Exception {
        // Prepare
        Person person1 = setUp();
        Person person2 = setUp();

        // Execute
        MvcResult mvcResult = mockMvc.perform(get("/person")
                .param("outputType", "simple") // Parámetro outputType
        ).andExpect(status().isOk()).andReturn();

        // Verify
        List<PersonOutputDTO> actualPersons = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {});
        List<PersonOutputDTO> expectedPersons = personService.getPersons("simple");
        assertThat(actualPersons).usingRecursiveComparison().isEqualTo(expectedPersons);
    }


    @Test
    void testGetPersonId() throws Exception {
        // Prepare
        Person person = setUp();

        // Execute
        MvcResult mvcResult =
                mockMvc.perform(get("/person/" + person.getIdPerson())
                ).andExpect(status().isOk()).andReturn();

        // Verify
        Person actualPerson = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Person.class);
        assertThat(actualPerson).isEqualToComparingFieldByField(person);
    }

    @Test
    void testGetPersonName() throws Exception {
        // Prepare
        Person person = setUp();

        // Execute
        MvcResult mvcResult =
                mockMvc.perform(get("/person/name/" + person.getName())
                ).andExpect(status().isOk()).andReturn();

        // Verify
        List<Person> actualPersons = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<Person>>() {});
        assertThat(actualPersons).usingElementComparatorOnFields("idPerson", "username", "password", "name", "companyEmail", "personalEmail", "city", "active", "imageUrl").contains(person);
    }

    @Test
    void updatePerson() throws Exception {
        // Prepare
        Person person = setUp();
        PersonInputDTO personInputDTO = new PersonInputDTO();
        personInputDTO.setUsername("UserTest");
        personInputDTO.setName("Test");
        personInputDTO.setPassword("test");
        personInputDTO.setPersonalEmail("test@email.com");
        personInputDTO.setCompanyEmail("test@bosonit.com");
        personInputDTO.setCity("Test");
        personInputDTO.setImagenUrl("https://imagetest.jpg");
        personInputDTO.setActive(true);


        // Execute
        MvcResult mvcResult =
                mockMvc.perform(put("/person/" + person.getIdPerson())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(personInputDTO))
                ).andExpect(status().isOk()).andReturn();

        // Verify
        Person actualPerson = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Person.class);
        Person expectedPerson = new Person(personInputDTO);
        expectedPerson.setIdPerson(person.getIdPerson());
        assertThat(actualPerson).isEqualToComparingFieldByField(expectedPerson);
    }

    @Test
    void testDeletePerson() throws Exception {
        // Prepare
        Person person = setUp();

        // Execute
        MvcResult mvcResult =
                mockMvc.perform(delete("/person/" + person.getIdPerson())
                ).andExpect(status().isOk()).andReturn();

        // Verify
        assertThat(personRepository.count()).isZero();
    }

}
