package com.example.block7crudvalidation.student.application;

import com.example.block7crudvalidation.student.domain.Student;
import com.example.block7crudvalidation.student.infrastructure.dto.input.StudentInputDTO;
import com.example.block7crudvalidation.student.infrastructure.dto.output.StudentOutputDTO;



import java.util.List;

public interface StudentService{
    StudentOutputDTO addStudent(StudentInputDTO studentInputDTO);
    StudentOutputDTO addSubjects(String idStudent, List<String> subjectsInsert);
    Student getStudentId(String id);
    StudentOutputDTO updateStudent(String id, StudentInputDTO studentInputDTO);
    void deleteStudent(String id);
    StudentOutputDTO deleteSubjects(String idStudent, List<String> subjectsDelete);

}