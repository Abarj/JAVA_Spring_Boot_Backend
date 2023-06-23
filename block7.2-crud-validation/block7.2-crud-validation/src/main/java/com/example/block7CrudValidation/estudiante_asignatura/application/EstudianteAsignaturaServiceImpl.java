<<<<<<< HEAD
package com.example.block7CrudValidation.estudiante_asignatura.application;

import com.example.block7CrudValidation.estudiante_asignatura.domain.EstudianteAsignatura;
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
    public EstudianteAsignatura getById(int id) {
        return estudianteAsignaturaRepository.findById(id).orElse(null);
    }

    @Override
    public List<EstudianteAsignatura> getAll() {
        return estudianteAsignaturaRepository.findAll();
    }

    @Override
    public List<EstudianteAsignatura> getByStudent(int id_student) {
        return estudianteAsignaturaRepository.findByStudentId(id_student);
    }

    @Override
    public void deleteEstudianteAsignatura(int id) {
        estudianteAsignaturaRepository.deleteById(id);
    }
}
=======
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
>>>>>>> 0215ed2c784a238139adc681a90ff64f94587231
