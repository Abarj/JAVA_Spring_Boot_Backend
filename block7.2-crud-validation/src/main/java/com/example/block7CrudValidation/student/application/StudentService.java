package com.example.block7CrudValidation.student.application;

import com.example.block7CrudValidation.exceptions.EntityNotFoundException;
import com.example.block7CrudValidation.student.domain.Student;

import java.util.ArrayList;
import java.util.List;

public interface StudentService {
    Student createStudent(Student student);
    Student updateStudent(Student student);
    void deleteStudent(int id);
    List<Student> getAllStudents();
    Student getStudentById(int id);
    public default List<Student> readEveryStudent() {
        List<Student> estudiantes = new ArrayList<>();
        return estudiantes;
    }
    Student getById(List<Student> estudiantes, int id);
    boolean filterByID(List<Student> estudiantes, int id);
    void assignAsignaturas(int studentId, List<Integer> asignaturaIds) throws EntityNotFoundException;
    void unassignAsignaturas(int studentId, List<Integer> asignaturaIds) throws EntityNotFoundException;
}

