package com.example.block7CrudValidation.asignatura.infrastructure.dto;

import com.example.block7CrudValidation.student.domain.Student;

import java.util.Date;

public class DtoAsignatura {
    private int id_subject;
    private Student student;
    private String subject;
    private Date initial_date;
    private Date finish_date;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getSubject() {
        return subject;
    }

    public Date getInitial_date() {
        return initial_date;
    }

    public Date getFinish_date() {
        return finish_date;
    }
}
