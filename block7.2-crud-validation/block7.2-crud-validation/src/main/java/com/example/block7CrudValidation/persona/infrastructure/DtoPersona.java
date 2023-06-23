package com.example.block7CrudValidation.persona.infrastructure;

import com.example.block7CrudValidation.persona.domain.Persona;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

@Data
public class DtoPersona implements Serializable {

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

    public static DtoPersona fromEntity(Persona persona) {
        DtoPersona dto = new DtoPersona();
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
<<<<<<< HEAD
=======

>>>>>>> 0215ed2c784a238139adc681a90ff64f94587231
    public Persona toEntity() {
        Persona persona = new Persona();
        persona.setId_person(this.getId_person());
        persona.setUsername(this.getUsername());
        persona.setName(this.getName());
        persona.setSurname(this.getSurname());
        persona.setCompany_email(this.getCompany_email());
        persona.setPersonal_email(this.getPersonal_email());
        persona.setCity(this.getCity());
        persona.setCreated_date(this.getCreated_date());
        persona.setImage_url(this.getImage_url());
        persona.setTermination_date(this.getTermination_date());
        return persona;
    }
    public DtoPersona getPersonInfo(Persona persona) {

        this.setUsername(persona.getUsername());
        this.setName(persona.getName());
        this.setSurname(persona.getSurname());
        this.setCompany_email(persona.getCompany_email());
        this.setPersonal_email(persona.getPersonal_email());
        this.setCity(persona.getCity());
        this.setCreated_date(persona.getCreated_date());
        this.setImage_url(persona.getImage_url());
        this.setTermination_date(persona.getTermination_date());
        return null;
    }
}
