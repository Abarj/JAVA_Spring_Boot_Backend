package com.example.block7crudvalidation.student.infrastructure.dto.output;

import com.example.block7crudvalidation.student.domain.Student;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class StudentFullOutputDTO extends StudentOutputDTO {

    private String idPerson;
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
        setIdPerson(student.getPerson().getIdPerson());
        setUsername(student.getPerson().getUsername());
        setName(student.getPerson().getName());
        setSurname(student.getPerson().getSurname());
        setCompanyEmail(student.getPerson().getCompanyEmail());
        setPersonalEmail(student.getPerson().getPersonalEmail());
        setCity(student.getPerson().getCity());
        setActive(student.getPerson().isActive());
        setCreatedDate(student.getPerson().getCreatedDate());
        setImageUrl(student.getPerson().getImageUrl());
        setTerminationDate(student.getPerson().getTerminationDate());
    }
}