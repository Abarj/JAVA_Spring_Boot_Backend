package com.example.block7CrudValidation.student.infrastructure.dto;

import com.example.block7CrudValidation.persona.infrastructure.dto.PersonDTO;
import com.example.block7CrudValidation.profesor.infrastructure.dto.DtoProfesor;
import com.example.block7CrudValidation.student.domain.Student;
import lombok.Data;

import java.io.Serializable;

@Data
public class StudentDTO implements Serializable {

    private int id_student;
    private PersonDTO persona;
    private int num_hours_week;
    private String comments;
    private DtoProfesor professor;
    private String branch;

    public static StudentDTO fromEntity(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setId_student(student.getId_student());
        dto.setPersona(PersonDTO.fromEntity(student.getPersona()));
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

    public void getStudentSimpleInfo(Student student) {
        this.setNum_hours_week(student.getNum_hours_week());
        this.setComments(student.getComments());
        this.setBranch(student.getBranch());
    }
}

