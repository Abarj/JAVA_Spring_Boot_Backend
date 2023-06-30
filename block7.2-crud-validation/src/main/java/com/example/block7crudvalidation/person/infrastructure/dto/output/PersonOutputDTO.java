package com.example.block7crudvalidation.person.infrastructure.dto.output;

import com.example.block7crudvalidation.person.domain.Person;
import lombok.Data;

import java.util.Date;

@Data
public class PersonOutputDTO {

    private String idPerson;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String companyEmail;
    private String personalEmail;
    private String city;
    private boolean active;
    private Date createdDate;
    private String imageUrl;
    private Date terminationDate;

    public PersonOutputDTO(Person person) {
        setIdPerson(person.getIdPerson());
        setUsername(person.getUsername());
        setPassword(person.getPassword());
        setName(person.getName());
        setSurname(person.getSurname());
        setCompanyEmail(person.getCompanyEmail());
        setPersonalEmail(person.getPersonalEmail());
        setCity(person.getCity());
        setActive(person.isActive());
        setCreatedDate(person.getCreatedDate());
        setImageUrl(person.getImageUrl());
        setTerminationDate(person.getTerminationDate());
    }
}