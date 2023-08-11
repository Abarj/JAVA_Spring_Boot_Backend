package com.example.block7crudvalidation.domain.person;

import com.example.block7crudvalidation.person.domain.Person;

public class PersonMother {

    public static Person mockPerson(Integer idPerson, String username, String name, String companyEmail) {

        Person person = new Person();
        person.setIdPerson(idPerson);
        person.setUsername(username);
        person.setName(name);
        person.setCompanyEmail(companyEmail);

        return person;
    }
}