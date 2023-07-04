package com.example.block7crudvalidation.student.application;

import com.example.block7crudvalidation.student.domain.Student;
import com.example.block7crudvalidation.student.infrastructure.dto.input.StudentInputDTO;
import com.example.block7crudvalidation.student.infrastructure.dto.output.StudentOutputDTO;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public interface StudentService{
    StudentOutputDTO addStudent(StudentInputDTO studentInputDTO);
    StudentOutputDTO addSubjects(Integer idStudent, Collection<Integer> subjectsInsert);
    Student getStudentId(Integer id);
    StudentOutputDTO updateStudent(Integer id, StudentInputDTO studentInputDTO);
    void deleteStudent(Integer id);
    StudentOutputDTO deleteSubjects(Integer idStudent, List<Integer> subjectsDelete);

}