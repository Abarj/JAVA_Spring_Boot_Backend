package com.example.block7CrudValidation.asignatura.infrastructure.controllers;

import com.example.block7CrudValidation.asignatura.application.AsignaturaService;
import com.example.block7CrudValidation.asignatura.domain.Asignatura;
import com.example.block7CrudValidation.asignatura.infrastructure.dto.DtoAsignatura;
import com.example.block7CrudValidation.student.application.StudentService;
import com.example.block7CrudValidation.student.domain.Student;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/asignatura")
public class AsignaturaController {

    @Autowired
    private AsignaturaService asignaturaService;

    @Autowired
    StudentService studentService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<Asignatura> createAsignatura(@RequestBody DtoAsignatura dtoAsignatura) {
        Asignatura asignatura = convertToEntity(dtoAsignatura);
        Asignatura createdAsignatura = asignaturaService.createEstudianteAsignatura(asignatura);
        return ResponseEntity.ok(createdAsignatura);
    }

    @GetMapping
    public ResponseEntity<List<Asignatura>> getAllAsignaturas() {
        List<Asignatura> asignaturas = asignaturaService.getAll();
        return ResponseEntity.ok(asignaturas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Asignatura> getAsignaturaById(@PathVariable int id) {
        Asignatura asignatura = asignaturaService.getById(id);
        if (asignatura != null) {
            return ResponseEntity.ok(asignatura);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/student/{id}")
    public List<Asignatura> getEstudianteAsignaturasById(@PathVariable("id") int studentId) {
        List<Asignatura> asignaturas = asignaturaService.getByEstudianteId(studentId);
        return asignaturas;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Asignatura> updateEstudianteAsignatura(@PathVariable int id, @RequestBody DtoAsignatura dtoAsignatura) {
        Asignatura existingAsignatura = asignaturaService.getById(id);
        if (existingAsignatura != null) {
            Asignatura updatedAsignatura = convertToEntity(dtoAsignatura);
            updatedAsignatura.setId_subject(existingAsignatura.getId_subject());
            Asignatura savedAsignatura = asignaturaService.updateEstudianteAsignatura(updatedAsignatura);
            return ResponseEntity.ok(savedAsignatura);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEstudianteAsignatura(@PathVariable int id) {
        Asignatura existingAsignatura = asignaturaService.getById(id);
        if (existingAsignatura != null) {
            asignaturaService.deleteEstudianteAsignatura(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private DtoAsignatura convertToDto(Asignatura asignatura) {
        return modelMapper.map(asignatura, DtoAsignatura.class);
    }

    private Asignatura convertToEntity(DtoAsignatura dtoAsignatura) {
        return modelMapper.map(dtoAsignatura, Asignatura.class);
    }
}
