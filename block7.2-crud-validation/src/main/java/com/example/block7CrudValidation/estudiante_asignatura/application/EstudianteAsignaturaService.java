package com.example.block7CrudValidation.estudiante_asignatura.application;

import com.example.block7CrudValidation.estudiante_asignatura.domain.EstudianteAsignatura;
import java.util.List;

public interface EstudianteAsignaturaService {

    EstudianteAsignatura createEstudianteAsignatura(EstudianteAsignatura estudianteAsignatura);
    EstudianteAsignatura updateEstudianteAsignatura(EstudianteAsignatura estudianteAsignatura);
    EstudianteAsignatura getById(String id);
    List<EstudianteAsignatura> getAll();
    List<EstudianteAsignatura> getByStudent(String idStudent);
    void deleteEstudianteAsignatura(String id);
}
