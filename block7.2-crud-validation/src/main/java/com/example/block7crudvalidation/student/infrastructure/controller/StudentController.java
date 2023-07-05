package com.example.block7crudvalidation.student.infrastructure.controller;

import com.example.block7crudvalidation.student.infrastructure.dto.input.StudentInputDTO;
import com.example.block7crudvalidation.student.infrastructure.dto.output.StudentOutputDTO;
import com.example.block7crudvalidation.student.infrastructure.dto.output.StudentFullOutputDTO;
import com.example.block7crudvalidation.student.application.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping
    public StudentOutputDTO addStudent(@Valid @RequestBody StudentInputDTO studentInputDTO) {
        return studentService.addStudent(studentInputDTO);
    }

    @GetMapping("/{id}")
    public StudentOutputDTO getStudentId(@PathVariable(name = "id") Integer id,
                                         @RequestParam(name = "outputType", defaultValue = "simple") String outputType) {
        if (outputType.equals("full")) {
            return new StudentFullOutputDTO(studentService.getStudentId(id));
        } else {
            return new StudentOutputDTO(studentService.getStudentId(id));
        }
    }

    @GetMapping
    public List<StudentOutputDTO> getAllStudents(@RequestParam(name = "outputType", defaultValue = "simple") String outputType) {
        return studentService.getAllStudents(outputType);
    }

    @PutMapping("/{id}")
    public StudentOutputDTO updateStudent(@PathVariable("id") Integer id,
                                          @RequestBody StudentInputDTO studentInputDTO) {
        return studentService.updateStudent(id, studentInputDTO);
    }

    @PutMapping("/subjectAdd/{id}")
    public StudentOutputDTO addSubject(@PathVariable("id") Integer idStudent,
                                      @RequestBody Collection<Integer> subjects) {
        return studentService.addSubjects(idStudent, subjects);
    }

    @PutMapping("/subjectDelete/{id}")
    public StudentOutputDTO deleteSubject(@PathVariable("id") Integer idStudent,
                                         @RequestBody List<Integer> subjects) {
        return studentService.deleteSubjects(idStudent, subjects);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable("id") Integer id) {
        studentService.deleteStudent(id);
    }
}