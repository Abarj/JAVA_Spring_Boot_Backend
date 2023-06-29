package com.example.block7CrudValidation.persona.infrastructure.dto;

import com.example.block7CrudValidation.persona.domain.Persona;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PersonDTO implements Serializable {

    private int id_person;
    private String username;
    private String name;
    private String surname;
    private String company_email;
    private String personal_email;
    private String city;
    private Date created_date;
    private String image_url;
    private Date termination_date;

    public static PersonDTO fromEntity(Persona persona) {
        PersonDTO dto = new PersonDTO();
        dto.setId_person(persona.getId_person());
        dto.setUsername(persona.getUsername());
        dto.setName(persona.getName());
        dto.setSurname(persona.getSurname());
        dto.setCompany_email(persona.getCompany_email());
        dto.setPersonal_email(persona.getPersonal_email());
        dto.setCity(persona.getCity());
        dto.setCreated_date(persona.getCreated_date());
        dto.setImage_url(persona.getImage_url());
        dto.setTermination_date(persona.getTermination_date());
        return dto;
    }
    public Persona toEntity() {
        Persona persona = new Persona();
        persona.setId_person(this.id_person);
        persona.setUsername(this.username);
        persona.setName(this.name);
        persona.setSurname(this.surname);
        persona.setCompany_email(this.company_email);
        persona.setPersonal_email(this.personal_email);
        persona.setCity(this.city);
        persona.setCreated_date(this.created_date);
        persona.setImage_url(this.image_url);
        persona.setTermination_date(this.termination_date);
        return persona;
    }

    public void getPersonInfo(Persona persona) {
        setId_person(persona.getId_person());
        setUsername(persona.getUsername());
        setName(persona.getName());
        setSurname(persona.getSurname());
        setCompany_email(persona.getCompany_email());
        setPersonal_email(persona.getPersonal_email());
        setCity(persona.getCity());
        setCreated_date(persona.getCreated_date());
        setImage_url(persona.getImage_url());
        setTermination_date(persona.getTermination_date());
    }
}
