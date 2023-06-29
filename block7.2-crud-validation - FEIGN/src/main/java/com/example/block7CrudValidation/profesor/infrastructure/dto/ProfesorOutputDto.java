package com.example.block7CrudValidation.profesor.infrastructure.dto;

import com.example.block7CrudValidation.student.domain.Student;

import java.util.List;

public class ProfesorOutputDto {
    private int id;
    private String comments;
    private String branch;
    private List<Student> students;

}