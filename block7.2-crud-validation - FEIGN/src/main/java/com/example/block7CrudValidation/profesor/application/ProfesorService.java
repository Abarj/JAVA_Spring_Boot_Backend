package com.example.block7CrudValidation.profesor.application;

import com.example.block7CrudValidation.profesor.domain.Profesor;
import com.example.block7CrudValidation.profesor.infrastructure.dto.ProfesorOutputDto;

import java.util.ArrayList;
import java.util.List;

public interface ProfesorService {
    Profesor createProfesor(Profesor profesor);
    Profesor updateProfesor(Profesor profesor);
    Profesor getById(int id);
    List<Profesor> getAll();
    List<Profesor> getByBranch(String branch);
    void deleteProfesor(int id);
    public default List<Profesor> readEveryTeacher() {
        List<Profesor> profesores = new ArrayList<>();
        return profesores;
    }
    boolean filterByID(List<Profesor> listaProfesores, int id);
    Profesor getByID(List<Profesor> listTeacher, int id);
    ProfesorOutputDto getProfesorById(int id);
}

