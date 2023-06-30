package com.example.block7crudvalidation.person.infrastructure.dto.input;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class PersonInputDTO {

    private String idPerson;

    @NotEmpty(message = "Debe introducir un username.")
    @Size(min = 6, max = 10, message = "El username debe tener entre 6 y 10 carácteres.")
    private String username;

    @NotEmpty(message = "Debe introducir una contraseña.")
    private String password;

    @NotEmpty(message = "Debe introducir un nombre.")
    private String name;

    private String surname;

    @NotEmpty(message = "Debe introducir un email corporativo.")
    @Email(message = "Emain corporativo inválido.")
    private String companyEmail;

    @NotEmpty(message = "Debe introducir un email personal.")
    @Email(message = "Email personal inválido.")
    private String personalEmail;

    @NotEmpty(message = "Debe introducir una ciudad.")
    private String city;

    private boolean active;

    private Date createDate;

    private String imagenUrl;

    private Date terminationDate;
}