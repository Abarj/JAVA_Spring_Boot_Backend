package com.example.block7crudvalidation.person.application;

import com.example.block7crudvalidation.person.infrastructure.dto.input.PersonInputDTO;
import com.example.block7crudvalidation.person.infrastructure.dto.output.PersonOutputDTO;
import java.util.List;

public interface PersonService {
    PersonOutputDTO addPerson(PersonInputDTO personInputDto);
    PersonOutputDTO getPersonById(Integer id);
    List<PersonOutputDTO> getPersonName(String username);
    List<PersonOutputDTO> getPersons();
    PersonOutputDTO updatePerson(Integer id, PersonInputDTO personInputDto);
    void deletePerson(Integer id);
}