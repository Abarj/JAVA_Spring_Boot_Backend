package com.example.block7CrudValidation.asignatura.application;

import com.example.block7CrudValidation.asignatura.domain.Asignatura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AsignaturaServiceImpl implements AsignaturaService {

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    @Override
    public Asignatura createEstudianteAsignatura(Asignatura asignatura) {
        return asignaturaRepository.save(asignatura);
    }

    @Override
    public Asignatura updateEstudianteAsignatura(Asignatura asignatura) {
        Asignatura existingAsignatura = getById(asignatura.getId_subject());
        if (existingAsignatura != null) {
            existingAsignatura.setProfesor(asignatura.getProfesor());
            existingAsignatura.setStudent(asignatura.getStudent());
            existingAsignatura.setSubject(asignatura.getSubject());
            existingAsignatura.setInitial_date(asignatura.getInitial_date());
            existingAsignatura.setFinish_date(asignatura.getFinish_date());

            return asignaturaRepository.save(existingAsignatura);
        } else {
            throw new IllegalArgumentException("Asignatura no encontrada con el ID: " + asignatura.getId_subject());
        }
    }

    @Override
    public Asignatura getById(int id) {
        return asignaturaRepository.findById(id).orElse(null);
    }

    @Override
    public List<Asignatura> getAll() {
        return asignaturaRepository.findAll();
    }

    @Override
    public void deleteEstudianteAsignatura(int id) {
        asignaturaRepository.deleteById(id);
    }

    @Override
    public List<Asignatura> readEverySubject() {
        return asignaturaRepository.findAll();
    }

    @Override
    public List<Asignatura> getByEstudianteId(int studentId) {
        return asignaturaRepository.findByStudentId(studentId);
    }
}
