package com.example.block7CrudValidation.persona.infrastructure.dto;

import com.example.block7CrudValidation.profesor.domain.Profesor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherPersonDTO extends PersonDTO {

    //Persona
    private String username;
    private String password;
    private String name;
    private String surname;
    private String company_email;
    private String personal_email;
    private String city;
    private Boolean active;
    private Date created_date;
    private String image_url;
    private Date termination_date;

    //Profesor
    private int id_profesor;
    private String comments;
    private String branch;

    public TeacherPersonDTO getTeacherPersonInfo(Profesor profesor) {

        //Parte de Profesor
        this.setId_profesor(profesor.getId_profesor());
        this.setComments(profesor.getComments());
        this.setBranch(profesor.getBranch());

        //Parte de Persona
        this.setUsername(profesor.getPersona().getUsername());
        this.setPassword(profesor.getPersona().getPassword());
        this.setName(profesor.getPersona().getName());
        this.setSurname(profesor.getPersona().getSurname());
        this.setCompany_email(profesor.getPersona().getCompany_email());
        this.setPersonal_email(profesor.getPersona().getPersonal_email());
        this.setCity(profesor.getPersona().getCity());
        this.setActive(profesor.getPersona().getActive());
        this.setCreated_date(profesor.getPersona().getCreated_date());
        this.setImage_url(profesor.getPersona().getImage_url());
        this.setTermination_date(profesor.getPersona().getTermination_date());
        return null;
    }
}