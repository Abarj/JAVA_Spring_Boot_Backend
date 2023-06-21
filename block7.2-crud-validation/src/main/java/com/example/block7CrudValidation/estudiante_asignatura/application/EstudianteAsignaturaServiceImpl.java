package com.example.block7CrudValidation.estudiante_asignatura.application;

import com.example.block7CrudValidation.estudiante_asignatura.domain.EstudianteAsignatura;
import com.example.block7CrudValidation.estudiante_asignatura.application.EstudianteAsignaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteAsignaturaServiceImpl implements EstudianteAsignaturaService {

    @Autowired
    private EstudianteAsignaturaRepository estudianteAsignaturaRepository;

    @Override
    public EstudianteAsignatura createEstudianteAsignatura(EstudianteAsignatura estudianteAsignatura) {
        return estudianteAsignaturaRepository.save(estudianteAsignatura);
    }

    @Override
    public EstudianteAsignatura updateEstudianteAsignatura(EstudianteAsignatura estudianteAsignatura) {
        return estudianteAsignaturaRepository.save(estudianteAsignatura);
    }

    @Override
    public EstudianteAsignatura getById(String id) {
        return estudianteAsignaturaRepository.findById(id).orElse(null);
    }

    @Override
    public List<EstudianteAsignatura> getAll() {
        return estudianteAsignaturaRepository.findAll();
    }

    @Override
    public List<EstudianteAsignatura> getByStudent(String idStudent) {
        return estudianteAsignaturaRepository.findByStudentId(idStudent);
    }

    @Override
    public void deleteEstudianteAsignatura(String id) {
        estudianteAsignaturaRepository.deleteById(id);
    }
}
