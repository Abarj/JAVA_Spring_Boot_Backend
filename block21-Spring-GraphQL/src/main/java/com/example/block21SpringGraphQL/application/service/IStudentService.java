package com.example.block21SpringGraphQL.application.service;

import com.example.block21SpringGraphQL.models.Student;

import java.util.List;

public interface IStudentService {

    void createStudent(Student student);
    List<Student> findAll();
    Student findById(Long id);
    void deleteById(Long id);
}
