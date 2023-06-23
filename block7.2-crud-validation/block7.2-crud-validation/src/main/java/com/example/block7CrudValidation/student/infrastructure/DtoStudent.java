<<<<<<< HEAD
package com.example.block7CrudValidation.student.infrastructure;

import com.example.block7CrudValidation.persona.infrastructure.DtoPersona;
import com.example.block7CrudValidation.profesor.infrastructure.DtoProfesor;
import com.example.block7CrudValidation.student.domain.Student;
import lombok.Data;

import java.io.Serializable;

@Data
public class DtoStudent implements Serializable {

    private int id_student;
    private DtoPersona persona;
    private int num_hours_week;
    private String comments;
    private DtoProfesor professor;
    private String branch;

    public static DtoStudent fromEntity(Student student) {
        DtoStudent dto = new DtoStudent();
        dto.setId_student(student.getId_student());
        dto.setPersona(DtoPersona.fromEntity(student.getPersona()));
        dto.setNum_hours_week(student.getNum_hours_week());
        dto.setComments(student.getComments());
        dto.setProfessor(DtoProfesor.fromEntity(student.getProfessor()));
        dto.setBranch(student.getBranch());
        return dto;
    }
    public Student toEntity() {
        Student student = new Student();
        student.setId_student(this.getId_student());
        student.setPersona(this.getPersona().toEntity());
        student.setNum_hours_week(this.getNum_hours_week());
        student.setComments(this.getComments());
        student.setProfessor(this.getProfessor().toEntity());
        student.setBranch(this.getBranch());
        return student;
    }
}
=======
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
>>>>>>> 0215ed2c784a238139adc681a90ff64f94587231
