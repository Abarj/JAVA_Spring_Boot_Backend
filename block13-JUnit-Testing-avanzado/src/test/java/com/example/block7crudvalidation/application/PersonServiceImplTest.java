package com.example.block7crudvalidation.application;

import com.example.block7crudvalidation.person.application.PersonServiceImpl;
import com.example.block7crudvalidation.person.domain.Person;
import com.example.block7crudvalidation.person.infrastructure.dto.input.PersonInputDTO;
import com.example.block7crudvalidation.person.infrastructure.dto.output.PersonOutputDTO;

import com.example.block7crudvalidation.person.infrastructure.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {

    @InjectMocks
    private PersonServiceImpl personService;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddPerson() {
        // Execute
        PersonInputDTO personInputDTO = new PersonInputDTO();
        personInputDTO.setUsername("abarj");
        personInputDTO.setName("Alvaro");
        personInputDTO.setCompanyEmail("alvaro@email.com");

        PersonOutputDTO result = personService.addPerson(personInputDTO);

        // Verify
        assertEquals("abarj", result.getUsername());
        assertEquals("Alvaro", result.getName());
        assertEquals(personInputDTO.getCompanyEmail(), result.getCompanyEmail());

        verify(personRepository, times(1)).save(any(Person.class));
    }

    @Test
    void testGetPersons() {
        // Setup
        Person person1 = new Person();
        person1.setIdPerson(1);
        person1.setUsername("abarj");
        person1.setName("Alvaro");
        person1.setCompanyEmail("alvaro@email.com");

        Person person2 = new Person();
        person2.setIdPerson(2);
        person2.setUsername("user2");
        person2.setName("Paula");
        person2.setCompanyEmail("paula@email.com");

        Person person3 = new Person();
        person3.setIdPerson(3);
        person3.setUsername("user3");
        person3.setName("Claudia");
        person3.setCompanyEmail("claudia@email.com");

        List<Person> personList = new ArrayList<>();
        personList.add(person1);
        personList.add(person2);
        personList.add(person3);

        when(personRepository.findAll()).thenReturn(personList);

        // Execute
        List<PersonOutputDTO> result = personService.getPersons("");

        // Verify
        assertEquals(3, result.size());

        PersonOutputDTO outputDTO1 = result.get(0);
        assertEquals(1, outputDTO1.getIdPerson());
        assertEquals("abarj", outputDTO1.getUsername());
        assertEquals("Alvaro", outputDTO1.getName());
        assertEquals(person1.getCompanyEmail(), outputDTO1.getCompanyEmail());

        PersonOutputDTO outputDTO2 = result.get(1);
        assertEquals(2, outputDTO2.getIdPerson());
        assertEquals("user2", outputDTO2.getUsername());
        assertEquals("Paula", outputDTO2.getName());
        assertEquals(person2.getCompanyEmail(), outputDTO2.getCompanyEmail());

        PersonOutputDTO outputDTO3 = result.get(2);
        assertEquals(3, outputDTO3.getIdPerson());
        assertEquals("user3", outputDTO3.getUsername());
        assertEquals("Claudia", outputDTO3.getName());
        assertEquals(person3.getCompanyEmail(), outputDTO3.getCompanyEmail());

        verify(personRepository, times(1)).findAll();
    }

    @Test
    void testGetPersonById() {
        // Setup
        Person person = new Person();
        person.setIdPerson(1);
        person.setUsername("abarj");
        person.setName("Alvaro");
        person.setCompanyEmail("alvaro@email.com");

        when(personRepository.findById(1)).thenReturn(Optional.of(person));

        // Execute
        PersonOutputDTO result = personService.getPersonById(1, "");

        // Verify
        assertEquals(1, result.getIdPerson());
        assertEquals("abarj", result.getUsername());
        assertEquals("Alvaro", result.getName());
        assertEquals(person.getCompanyEmail(), result.getCompanyEmail());

        verify(personRepository, times(1)).findById(1);
    }

    @Test
    void testGetPersonName() {
        // Setup
        Person person1 = new Person();
        person1.setIdPerson(1);
        person1.setUsername("abarj");
        person1.setName("Alvaro");
        person1.setCompanyEmail("alvaro@email.com");

        Person person2 = new Person();
        person2.setIdPerson(2);
        person2.setUsername("user2");
        person2.setName("Paula");
        person2.setCompanyEmail("paula@email.com");

        List<Person> personList = new ArrayList<>();
        personList.add(person1);
        personList.add(person2);

        when(personRepository.findByName("Alvaro")).thenReturn(personList);

        // Execute
        List<PersonOutputDTO> result = personService.getPersonName("Alvaro", "");

        // Verify
        assertEquals(2, result.size());

        PersonOutputDTO outputDTO1 = result.get(0);
        assertEquals(1, outputDTO1.getIdPerson());
        assertEquals("abarj", outputDTO1.getUsername());
        assertEquals("Alvaro", outputDTO1.getName());
        assertEquals(person1.getCompanyEmail(), outputDTO1.getCompanyEmail());

        PersonOutputDTO outputDTO2 = result.get(1);
        assertEquals(2, outputDTO2.getIdPerson());
        assertEquals("user2", outputDTO2.getUsername());
        assertEquals("Paula", outputDTO2.getName());
        assertEquals(person2.getCompanyEmail(), outputDTO2.getCompanyEmail());

        verify(personRepository, times(1)).findByName("Alvaro");
    }

    @Test
    void testUpdatePerson() {
        // Setup
        PersonInputDTO personInputDTO = new PersonInputDTO();
        personInputDTO.setName("UpdatedName");

        Person existingPerson = new Person();
        existingPerson.setIdPerson(1);
        existingPerson.setName("OldName");

        when(personRepository.findById(1)).thenReturn(Optional.of(existingPerson));
        when(personRepository.save(any(Person.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Execute
        PersonOutputDTO result = personService.updatePerson(1, personInputDTO);

        // Verify
        assertEquals(1, result.getIdPerson());
        assertEquals("UpdatedName", result.getName());

        verify(personRepository, times(1)).findById(1);
        verify(personRepository, times(1)).save(any(Person.class));
    }

    @Test
    void testDeletePerson() {
        // Setup
        Person person = new Person();
        person.setIdPerson(1);
        person.setUsername("abarj");
        person.setName("Alvaro");
        person.setCompanyEmail("alvaro@email.com");

        when(personRepository.findById(1)).thenReturn(Optional.of(person));

        // Execute
        personService.deletePerson(1);

        // Verify
        verify(personRepository, times(1)).findById(1);
        verify(personRepository, times(1)).delete(person);
    }
}
