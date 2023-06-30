package com.example.block7crudvalidation.person.application;

import com.example.block7crudvalidation.person.infrastructure.dto.input.PersonInputDTO;
import com.example.block7crudvalidation.person.infrastructure.dto.output.PersonOutputDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PersonService {
    PersonOutputDTO addPerson(PersonInputDTO personInputDto);
    PersonOutputDTO getPersonId(String id);
    List<PersonOutputDTO> getPersonName(String username);
    List<PersonOutputDTO> getPersons();
    ResponseEntity<PersonOutputDTO> getPersonId(String id, String outputType) throws Exception;
    PersonOutputDTO updatePerson(String id, PersonInputDTO personInputDto);
    void deletePerson(String id);
}