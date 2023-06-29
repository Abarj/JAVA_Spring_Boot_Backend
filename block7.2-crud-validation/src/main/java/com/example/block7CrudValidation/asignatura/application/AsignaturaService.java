package com.example.block7CrudValidation.asignatura.application;

import com.example.block7CrudValidation.asignatura.domain.Asignatura;
import java.util.List;

public interface AsignaturaService {

    Asignatura createEstudianteAsignatura(Asignatura asignatura);
    Asignatura updateEstudianteAsignatura(Asignatura asignatura);
    Asignatura getById(int id);
    List<Asignatura> getAll();
    void deleteEstudianteAsignatura(int id);
    List<Asignatura> readEverySubject();
    List<Asignatura> getByEstudianteId(int studentId);
}
