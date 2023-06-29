package com.example.block7CrudValidation.persona.infrastructure.dto;

import com.example.block7CrudValidation.student.domain.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentPersonDTO extends PersonDTO {

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

    //Estudiante
    private int id_student;
    private int num_hours_week;
    private String comments;
    private String branch;

    public StudentPersonDTO getStudentPersonInfo(Student student) {
        //Parte de Student
        this.setId_student(student.getId_student());
        this.setNum_hours_week(student.getNum_hours_week());
        this.setComments(student.getComments());
        this.setBranch(student.getBranch());
        //Parte de Persona
        this.setUsername(student.getPersona().getUsername());
        this.setPassword(student.getPersona().getPassword());
        this.setName(student.getPersona().getName());
        this.setSurname(student.getPersona().getSurname());
        this.setCompany_email(student.getPersona().getCompany_email());
        this.setPersonal_email(student.getPersona().getPersonal_email());
        this.setCity(student.getPersona().getCity());
        this.setActive(student.getPersona().getActive());
        this.setCreated_date(student.getPersona().getCreated_date());
        this.setImage_url(student.getPersona().getImage_url());
        this.setTermination_date(student.getPersona().getTermination_date());
        return null;
    }
}