package com.example.block7crudvalidation.person.infrastructure.dto.output;


import com.example.block7crudvalidation.person.domain.Person;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PersonOutputDTO {

    private Integer idPerson;
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

    public PersonOutputDTO(Person person)  {
        this.idPerson = person.getIdPerson();
        this.username = person.getUsername();
        this.password = person.getPassword();
        this.name = person.getName();
        this.surname = person.getSurname();
        this.companyEmail = person.getCompanyEmail();
        this.personalEmail = person.getPersonalEmail();
        this.city = person.getCity();
        this.active = person.isActive();
        this.createdDate = person.getCreatedDate();
        this.imageUrl = person.getImageUrl();
        this.terminationDate = person.getTerminationDate();
    }
}