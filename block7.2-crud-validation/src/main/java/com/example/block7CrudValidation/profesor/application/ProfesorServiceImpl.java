package com.example.block7CrudValidation.profesor.application;

import com.example.block7CrudValidation.profesor.domain.Profesor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesorServiceImpl implements ProfesorService {

    @Autowired
    private ProfesorRepository profesorRepository;

    @Override
    public Profesor createProfesor(Profesor profesor) {
        return profesorRepository.save(profesor);
    }

    @Override
    public Profesor updateProfesor(Profesor profesor) {
        return profesorRepository.save(profesor);
    }

    @Override
    public Profesor getById(int id) {
        return profesorRepository.findById(id).orElse(null);
    }

    @Override
    public List<Profesor> getAll() {
        return profesorRepository.findAll();
    }

    @Override
    public List<Profesor> getByBranch(String branch) {
        return profesorRepository.findByBranch(branch);
    }

    @Override
    public void deleteProfesor(int id) {
        profesorRepository.deleteById(id);
    }

    @Override
    public List<Profesor> readEveryTeacher() {
        List<Profesor> profesores = profesorRepository.findAll();
        return profesores;
    }

    @Override
    public boolean filterByID(List<Profesor> listTeacher, int id) {
        for (Profesor profesor : listTeacher) {
            // Comparamos el ID del profesor con el ID proporcionado
            if (profesor.getId_profesor() == id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Profesor getByID(List<Profesor> listTeacher, int id) {
        for (Profesor profesor : listTeacher) {
            if (profesor.getId_profesor() == id) {
                return profesor;
            }
        }
        return null;
    }
}
