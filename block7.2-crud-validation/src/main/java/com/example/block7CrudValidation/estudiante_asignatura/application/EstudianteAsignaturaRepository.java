package com.example.block7CrudValidation.estudiante_asignatura.application;

import com.example.block7CrudValidation.estudiante_asignatura.domain.EstudianteAsignatura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstudianteAsignaturaRepository extends JpaRepository<EstudianteAsignatura, String> {
    List<EstudianteAsignatura> findByStudentId(String idStudent);
}
