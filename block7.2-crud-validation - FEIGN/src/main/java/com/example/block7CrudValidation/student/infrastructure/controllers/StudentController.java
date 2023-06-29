package com.example.block7CrudValidation.student.infrastructure.controllers;

import com.example.block7CrudValidation.exceptions.EntityNotFoundException;
import com.example.block7CrudValidation.student.application.StudentService;
import com.example.block7CrudValidation.student.domain.Student;
import com.example.block7CrudValidation.student.infrastructure.dto.StudentDTO;
import com.example.block7CrudValidation.student.infrastructure.dto.FullStudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/estudiante")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student createdStudent = studentService.createStudent(student);
        return ResponseEntity.ok(createdStudent);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public StudentDTO readStudentById(@PathVariable("id") int id, @RequestParam(name = "outputType", defaultValue = "simple") String type) throws EntityNotFoundException {
        Student student = (Student) studentService.getStudentById(id);

        if (type.equals("full")) {
            FullStudentDTO fullStudentDTO = new FullStudentDTO();
            fullStudentDTO.getFullStudentInfo(student);
            return fullStudentDTO;
        } else {
            StudentDTO simpleStudentDTO = new StudentDTO();
            simpleStudentDTO.getStudentSimpleInfo(student);
            return simpleStudentDTO;
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable(name = "id") int id, @RequestBody Student student) {
        Student existingStudent = studentService.getStudentById(id);
        if (existingStudent != null) {
            student.setId_student(id);

            // Conservar la persona existente del estudiante
            student.setPersona(existingStudent.getPersona());

            Student updatedStudent = studentService.updateStudent(student);
            return ResponseEntity.ok(updatedStudent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok("El estudiante con el ID: " + id + " ha sido eliminado.");
    }

    @PostMapping("/{studentId}/asignaturas")
    public ResponseEntity<String> assignAsignaturas(@PathVariable("studentId") int studentId, @RequestBody List<Integer> asignaturaIds) throws EntityNotFoundException {
        studentService.assignAsignaturas(studentId, asignaturaIds);
        return ResponseEntity.ok("Asignaturas asignadas correctamente al estudiante con ID: " + studentId);
    }

    @DeleteMapping("/{studentId}/asignaturas")
    public ResponseEntity<String> unassignAsignaturas(@PathVariable("studentId") int studentId, @RequestBody List<Integer> asignaturaIds) throws EntityNotFoundException {
        studentService.unassignAsignaturas(studentId, asignaturaIds);
        return ResponseEntity.ok("Asignaturas desasignadas correctamente del estudiante con ID: " + studentId);
    }
}