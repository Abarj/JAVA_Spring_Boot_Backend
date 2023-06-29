package com.example.block7CrudValidation.profesor.infrastructure.dto;

import com.example.block7CrudValidation.profesor.domain.Profesor;
import com.example.block7CrudValidation.persona.infrastructure.dto.PersonDTO;
import lombok.Data;

import java.io.Serializable;

@Data
public class DtoProfesor implements Serializable {

    private int id_profesor;
    private PersonDTO persona;
    private String comments;
    private String branch;

    public static DtoProfesor fromEntity(Profesor profesor) {
        DtoProfesor dto = new DtoProfesor();
        dto.setId_profesor(profesor.getId_profesor());
        dto.setPersona(PersonDTO.fromEntity(profesor.getPersona()));
        dto.setComments(profesor.getComments());
        dto.setBranch(profesor.getBranch());
        return dto;
    }
    public Profesor toEntity() {
        Profesor profesor = new Profesor();
        profesor.setId_profesor(this.getId_profesor());
        profesor.setPersona(this.getPersona().toEntity());
        profesor.setComments(this.getComments());
        profesor.setBranch(this.getBranch());
        return profesor;
    }
}
