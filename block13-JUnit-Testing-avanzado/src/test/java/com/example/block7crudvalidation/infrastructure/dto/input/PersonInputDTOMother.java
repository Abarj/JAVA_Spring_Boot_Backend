package com.example.block7crudvalidation.infrastructure.dto.input;

import com.example.block7crudvalidation.person.infrastructure.dto.input.PersonInputDTO;

public class PersonInputDTOMother {

    public static PersonInputDTO mockPersonDTO() {

        PersonInputDTO personInputDTO = new PersonInputDTO();
        personInputDTO.setUsername("abarj");
        personInputDTO.setName("Alvaro");
        personInputDTO.setCompanyEmail("alvaro@email.com");

        return personInputDTO;
    }
}