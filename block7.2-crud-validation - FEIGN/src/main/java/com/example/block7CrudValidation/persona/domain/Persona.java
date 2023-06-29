package com.example.block7CrudValidation.persona.domain;

import com.example.block7CrudValidation.exceptions.CreateUserException;
import lombok.Data;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table
public class Persona implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id_person;
    @Column
    String username;
    @Column
    String password;
    @Column
    String name;
    @Column
    String surname;
    @Column
    String company_email;
    @Column
    String personal_email;
    @Column
    String city;
    @Column
    Boolean active;
    @Column
    Date created_date;
    @Column
    String image_url;
    @Column
    Date termination_date;

    public Boolean validarDatos() throws CreateUserException {
        if (this.username == null) {
            throw new CreateUserException("Debe introducir un usuario");
        }
        if (this.username.length() > 10) {
            throw new CreateUserException("La longitud del usuario no puede ser superior a 10 caracteres");
        }
        if (this.password == null || this.password.isEmpty()) {
            throw new CreateUserException("Debe introducir una contraseña");
        }
        if (this.name == null || this.name.isEmpty()) {
            throw new CreateUserException("Debe introducir un nombre");
        }
        if (this.surname == null || this.surname.isEmpty()) {
            throw new CreateUserException("Debe introducir un apellido");
        }
        if (this.company_email == null || this.company_email.isEmpty()) {
            throw new CreateUserException("Debe introducir un email corporativo válido");
        }
        if (this.personal_email == null || this.personal_email.isEmpty()) {
            throw new CreateUserException("Debe introducir un email personal válido");
        }
        if (this.city == null || this.city.isEmpty()) {
            throw new CreateUserException("Debe introducir una ciudad");
        }
        if (this.active == null) {
            throw new CreateUserException("Debe asignar un valor a Active");
        }
        if (this.created_date == null) {
            throw new CreateUserException("Debe introducir una fecha de creación");
        }

        return true;
    }

    public int getId_person() {
        return id_person;
    }

    public void setId_person(int id_person) {
        this.id_person = id_person;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCompany_email() {
        return company_email;
    }

    public void setCompany_email(String company_email) {
        this.company_email = company_email;
    }

    public String getPersonal_email() {
        return personal_email;
    }

    public void setPersonal_email(String personal_email) {
        this.personal_email = personal_email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public Date getTermination_date() {
        return termination_date;
    }

    public void setTermination_date(Date termination_date) {
        this.termination_date = termination_date;
    }
}