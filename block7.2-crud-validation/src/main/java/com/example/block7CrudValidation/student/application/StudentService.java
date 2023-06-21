package com.example.block7CrudValidation.student.application;

import com.example.block7CrudValidation.student.domain.Student;
import java.util.List;

public interface StudentService {
    Student createStudent(Student student);
    Student updateStudent(Student student);
    void deleteStudent(String id);

    List<Student> getAllStudents();

    Student getStudentById(String id);
}