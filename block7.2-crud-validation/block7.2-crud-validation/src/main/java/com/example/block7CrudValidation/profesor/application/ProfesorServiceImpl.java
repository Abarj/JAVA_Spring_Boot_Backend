<<<<<<< HEAD
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
}
=======
package com.example.block7CrudValidation.profesor.application;

import com.example.block7CrudValidation.profesor.application.ProfesorRepository;
import com.example.block7CrudValidation.profesor.application.ProfesorService;
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
    public Profesor getById(String id) {
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
    public void deleteProfesor(String id) {
        profesorRepository.deleteById(id);
    }
}
>>>>>>> 0215ed2c784a238139adc681a90ff64f94587231
