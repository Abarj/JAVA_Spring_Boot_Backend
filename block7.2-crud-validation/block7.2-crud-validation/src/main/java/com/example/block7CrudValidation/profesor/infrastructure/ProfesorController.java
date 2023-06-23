<<<<<<< HEAD
package com.example.block7CrudValidation.profesor.infrastructure;

import com.example.block7CrudValidation.profesor.application.ProfesorService;
import com.example.block7CrudValidation.profesor.domain.Profesor;
import com.example.block7CrudValidation.profesor.infrastructure.DtoProfesor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/profesores")
public class ProfesorController {
    @Autowired
    private ProfesorService profesorService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<DtoProfesor> createProfesor(@RequestBody DtoProfesor dtoProfesor) {
        Profesor profesor = modelMapper.map(dtoProfesor, Profesor.class);
        profesor = profesorService.createProfesor(profesor);
        DtoProfesor responseDto = modelMapper.map(profesor, DtoProfesor.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    /*
    @PutMapping("/{id}")
    public ResponseEntity<DtoProfesor> updateProfesor(@PathVariable int id, @RequestBody DtoProfesor dtoProfesor) {
        Profesor existingProfesor = profesorService.getById(id);
        if (existingProfesor != null) {
            Profesor profesor = modelMapper.map(dtoProfesor, Profesor.class);
            profesor.setId_profesor(id);
            profesor = profesorService.updateProfesor(profesor);
            DtoProfesor responseDto = modelMapper.map(profesor, DtoProfesor.class);
            return ResponseEntity.ok(responseDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
     */

    @GetMapping("/{id}")
    public ResponseEntity<DtoProfesor> getProfesorById(@PathVariable int id) {
        Profesor profesor = profesorService.getById(id);
        if (profesor != null) {
            DtoProfesor dtoProfesor = modelMapper.map(profesor, DtoProfesor.class);
            return ResponseEntity.ok(dtoProfesor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<DtoProfesor>> getAllProfesores() {
        List<Profesor> profesores = profesorService.getAll();
        List<DtoProfesor> dtoProfesores = profesores.stream()
                .map(profesor -> modelMapper.map(profesor, DtoProfesor.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtoProfesores);
    }

    @GetMapping("/branch/{branch}")
    public ResponseEntity<List<DtoProfesor>> getProfesoresByBranch(@PathVariable String branch) {
        List<Profesor> profesores = profesorService.getByBranch(branch);
        List<DtoProfesor> dtoProfesores = profesores.stream()
                .map(profesor -> modelMapper.map(profesor, DtoProfesor.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtoProfesores);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfesor(@PathVariable int id) {
        Profesor existingProfesor = profesorService.getById(id);
        if (existingProfesor != null) {
            profesorService.deleteProfesor(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
=======
package com.example.block7CrudValidation.profesor.infrastructure;

import com.example.block7CrudValidation.profesor.application.ProfesorService;
import com.example.block7CrudValidation.profesor.domain.Profesor;
import com.example.block7CrudValidation.profesor.infrastructure.DtoProfesor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/profesores")
public class ProfesorController {

    @Autowired
    private ProfesorService profesorService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<DtoProfesor> createProfesor(@RequestBody DtoProfesor dtoProfesor) {
        Profesor profesor = modelMapper.map(dtoProfesor, Profesor.class);
        profesor = profesorService.createProfesor(profesor);
        DtoProfesor responseDto = modelMapper.map(profesor, DtoProfesor.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DtoProfesor> updateProfesor(@PathVariable String id, @RequestBody DtoProfesor dtoProfesor) {
        Profesor existingProfesor = profesorService.getById(id);
        if (existingProfesor != null) {
            Profesor profesor = modelMapper.map(dtoProfesor, Profesor.class);
            profesor.setId_profesor(id);
            profesor = profesorService.updateProfesor(profesor);
            DtoProfesor responseDto = modelMapper.map(profesor, DtoProfesor.class);
            return ResponseEntity.ok(responseDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DtoProfesor> getProfesorById(@PathVariable String id) {
        Profesor profesor = profesorService.getById(id);
        if (profesor != null) {
            DtoProfesor dtoProfesor = modelMapper.map(profesor, DtoProfesor.class);
            return ResponseEntity.ok(dtoProfesor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<DtoProfesor>> getAllProfesores() {
        List<Profesor> profesores = profesorService.getAll();
        List<DtoProfesor> dtoProfesores = profesores.stream()
                .map(profesor -> modelMapper.map(profesor, DtoProfesor.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtoProfesores);
    }

    @GetMapping("/branch/{branch}")
    public ResponseEntity<List<DtoProfesor>> getProfesoresByBranch(@PathVariable String branch) {
        List<Profesor> profesores = profesorService.getByBranch(branch);
        List<DtoProfesor> dtoProfesores = profesores.stream()
                .map(profesor -> modelMapper.map(profesor, DtoProfesor.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtoProfesores);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfesor(@PathVariable String id) {
        Profesor existingProfesor = profesorService.getById(id);
        if (existingProfesor != null) {
            profesorService.deleteProfesor(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
>>>>>>> 0215ed2c784a238139adc681a90ff64f94587231
