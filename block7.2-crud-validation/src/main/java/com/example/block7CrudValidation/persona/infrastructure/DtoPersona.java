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
