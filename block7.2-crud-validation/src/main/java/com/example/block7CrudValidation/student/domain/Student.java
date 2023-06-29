package com.example.block7CrudValidation.student.domain;

import com.example.block7CrudValidation.persona.domain.Persona;
import com.example.block7CrudValidation.profesor.domain.Profesor;
import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_student")
    private int id;

    @OneToOne
    @JoinColumn(name = "id_persona")
    Persona persona;

    @Column(nullable = false)
    private int num_hours_week;

    private String comments;

    @ManyToOne
    @JoinColumn(name = "id_profesor")
    private Profesor professor;

    @Column(nullable = false)
    private String branch;

    public int getId_student() {
        return id;
    }

    public void setId_student(int id_student) {
        this.id = id_student;
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
