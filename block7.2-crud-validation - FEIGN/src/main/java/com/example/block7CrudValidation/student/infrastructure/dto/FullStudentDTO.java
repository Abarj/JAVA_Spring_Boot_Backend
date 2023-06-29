package com.example.block7CrudValidation.student.infrastructure.dto;

import com.example.block7CrudValidation.student.domain.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FullStudentDTO extends StudentDTO {
    //Student
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

    //Persona
    private int num_hours_week;
    private String comments;
    private String branch;

    public FullStudentDTO getFullStudentInfo(Student student) {
        if (student.getPersona() != null) {
            // Accede a los atributos de Persona solo si el objeto Persona no es null
            this.setUsername(student.getPersona().getUsername());
            this.setPassword(student.getPersona().getPassword());
            this.setName(student.getPersona().getName());
            this.setSurname(student.getPersona().getSurname());
            this.setCompany_email(student.getPersona().getCompany_email());
            this.setPersonal_email(student.getPersona().getPersonal_email());
            this.setCity(student.getPersona().getCity());
            this.setActive(student.getPersona().getActive());
            this.setCreated_date(new Date(student.getPersona().getCreated_date().getTime()));
            this.setImage_url(student.getPersona().getImage_url());
            this.setTermination_date(new Date(student.getPersona().getTermination_date().getTime()));        }

        // Setea los atributos de Student
        this.setNum_hours_week(student.getNum_hours_week());
        this.setComments(student.getComments());
        this.setBranch(student.getBranch());

        return this;
    }
}