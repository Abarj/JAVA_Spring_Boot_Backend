package com.example.block7CrudValidation.estudiante_asignatura.infrastructure;

import com.example.block7CrudValidation.estudiante_asignatura.application.EstudianteAsignaturaService;
import com.example.block7CrudValidation.estudiante_asignatura.domain.EstudianteAsignatura;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/estudiante_asignatura")
public class EstudianteAsignaturaController {

    @Autowired
    private EstudianteAsignaturaService estudianteAsignaturaService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<DtoEstudianteAsignatura> createEstudianteAsignatura(@RequestBody DtoEstudianteAsignatura dtoEstudianteAsignatura) {
        EstudianteAsignatura estudianteAsignatura = convertToEntity(dtoEstudianteAsignatura);
        EstudianteAsignatura createdEstudianteAsignatura = estudianteAsignaturaService.createEstudianteAsignatura(estudianteAsignatura);
        DtoEstudianteAsignatura responseDto = convertToDto(createdEstudianteAsignatura);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DtoEstudianteAsignatura> updateEstudianteAsignatura(@PathVariable String id, @RequestBody DtoEstudianteAsignatura dtoEstudianteAsignatura) {
        EstudianteAsignatura existingEstudianteAsignatura = estudianteAsignaturaService.getById(id);
        if (existingEstudianteAsignatura != null) {
            EstudianteAsignatura updatedEstudianteAsignatura = convertToEntity(dtoEstudianteAsignatura);
            updatedEstudianteAsignatura.setIdAsignatura(existingEstudianteAsignatura.getIdAsignatura());
            EstudianteAsignatura savedEstudianteAsignatura = estudianteAsignaturaService.updateEstudianteAsignatura(updatedEstudianteAsignatura);
            DtoEstudianteAsignatura responseDto = convertToDto(savedEstudianteAsignatura);
            return ResponseEntity.ok(responseDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DtoEstudianteAsignatura> getEstudianteAsignaturaById(@PathVariable String id) {
        EstudianteAsignatura estudianteAsignatura = estudianteAsignaturaService.getById(id);
        if (estudianteAsignatura != null) {
            DtoEstudianteAsignatura responseDto = convertToDto(estudianteAsignatura);
            return ResponseEntity.ok(responseDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<DtoEstudianteAsignatura>> getAllEstudianteAsignaturas() {
        List<EstudianteAsignatura> estudianteAsignaturas = estudianteAsignaturaService.getAll();
        List<DtoEstudianteAsignatura> responseDtoList = estudianteAsignaturas.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDtoList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEstudianteAsignatura(@PathVariable String id) {
        EstudianteAsignatura existingEstudianteAsignatura = estudianteAsignaturaService.getById(id);
        if (existingEstudianteAsignatura != null) {
            estudianteAsignaturaService.deleteEstudianteAsignatura(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private DtoEstudianteAsignatura convertToDto(EstudianteAsignatura estudianteAsignatura) {
        return modelMapper.map(estudianteAsignatura, DtoEstudianteAsignatura.class);
    }

    private EstudianteAsignatura convertToEntity(DtoEstudianteAsignatura dtoEstudianteAsignatura) {
        return modelMapper.map(dtoEstudianteAsignatura, EstudianteAsignatura.class);
    }
}
