package com.example.block7CrudValidation.profesor.infrastructure;

import com.example.block7CrudValidation.profesor.domain.Profesor;
import com.example.block7CrudValidation.persona.infrastructure.DtoPersona;
import lombok.Data;

import java.io.Serializable;

@Data
public class DtoProfesor implements Serializable {

    private int id_profesor;
    private DtoPersona persona;
    private String comments;
    private String branch;

    public static DtoProfesor fromEntity(Profesor profesor) {
        DtoProfesor dto = new DtoProfesor();
        dto.setId_profesor(Integer.parseInt(profesor.getId_profesor()));
        dto.setPersona(DtoPersona.fromEntity(profesor.getPersona()));
        dto.setComments(profesor.getComments());
        dto.setBranch(profesor.getBranch());
        return dto;
    }

    public Profesor toEntity() {
        Profesor profesor = new Profesor();
        profesor.setId_profesor(String.valueOf(this.getId_profesor()));
        profesor.setPersona(this.getPersona().toEntity());
        profesor.setComments(this.getComments());
        profesor.setBranch(this.getBranch());
        return profesor;
    }
}