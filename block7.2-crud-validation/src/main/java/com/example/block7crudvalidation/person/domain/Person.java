package com.example.block7crudvalidation.person.domain;

import com.example.block7crudvalidation.person.infrastructure.dto.input.PersonInputDTO;
import com.example.block7crudvalidation.student.domain.Student;
import com.example.block7crudvalidation.teacher.domain.Teacher;
import lombok.*;
import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Persons")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer idPerson;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String companyEmail;

    @Column
    private String personalEmail;

    @Column
    private String city;

    @Column
    private boolean active;

    @Column
    private Date createdDate;

    @Column
    private String imageUrl;

    @Column
    private Date terminationDate;

    @OneToOne(mappedBy = "person")
    private Student student;

    @OneToOne(mappedBy = "person")
    private Teacher teacher;

    public void update(PersonInputDTO personInputDto) {
        if (personInputDto.getUsername() != null && personInputDto.getUsername().length() <= 10 && personInputDto.getUsername().length() >= 6) {
            setUsername(personInputDto.getUsername());
        }
        if (personInputDto.getName() != null) {
            setName(personInputDto.getName());
        }
        if (personInputDto.getSurname() != null) {
            setSurname(personInputDto.getSurname());
        }
        if (personInputDto.getPassword() != null) {
            setPassword(personInputDto.getPassword());
        }
        if (personInputDto.getCompanyEmail() != null) {
            setCompanyEmail(personInputDto.getCompanyEmail());
        }
        if (personInputDto.getPersonalEmail() != null) {
            setPersonalEmail(personInputDto.getPersonalEmail());
        }
        if (personInputDto.getCity() != null) {
            setCity(personInputDto.getCity());
        }
        if (personInputDto.getImagenUrl() != null) {
            setImageUrl(personInputDto.getImagenUrl());
        }
    }

    public Person(PersonInputDTO personInputDTO) {
        this.username = personInputDTO.getUsername();
        this.password = personInputDTO.getPassword();
        this.name = personInputDTO.getName();
        this.surname = personInputDTO.getSurname();
        this.companyEmail = personInputDTO.getCompanyEmail();
        this.personalEmail = personInputDTO.getPersonalEmail();
        this.city = personInputDTO.getCity();
        this.active = personInputDTO.isActive();
        this.createdDate = personInputDTO.getCreateDate();
        this.imageUrl = personInputDTO.getImagenUrl();
        this.terminationDate = personInputDTO.getTerminationDate();
    }
}