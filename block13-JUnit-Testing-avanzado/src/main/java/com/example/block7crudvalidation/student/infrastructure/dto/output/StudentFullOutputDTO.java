package com.example.block7crudvalidation.student.infrastructure.dto.output;

import com.example.block7crudvalidation.person.domain.Person;
import com.example.block7crudvalidation.student.domain.Student;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentFullOutputDTO extends StudentOutputDTO {

    private Integer idPerson;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String companyEmail;
    private String personalEmail;
    private String city;
    private Boolean active;
    private Date createdDate;
    private String imageUrl;
    private Date terminationDate;

    public StudentFullOutputDTO(Student student) {
        super(student);
        Person person = student.getPerson();
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