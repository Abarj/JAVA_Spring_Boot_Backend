package com.example.block7CrudValidation.student.infrastructure;

import com.example.block7CrudValidation.persona.domain.Persona;
import com.example.block7CrudValidation.profesor.domain.Profesor;
import lombok.Data;

@Data
public class DtoStudent {

    private String id_student;
    private Persona persona;
    private int num_hours_week;
    private String comments;
    private Profesor professor;
    private String branch;

    public String getId_student() {
        return id_student;
    }

    public void setId_student(String id_student) {
        this.id_student = id_student;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public int getNum_hours_week() {
        return num_hours_week;
    }

    public void setNum_hours_week(int num_hours_week) {
        this.num_hours_week = num_hours_week;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Profesor getProfessor() {
        return professor;
    }

    public void setProfessor(Profesor professor) {
        this.professor = professor;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
}
