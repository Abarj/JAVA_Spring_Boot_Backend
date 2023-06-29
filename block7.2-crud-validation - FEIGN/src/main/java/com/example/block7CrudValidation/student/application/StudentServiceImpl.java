package com.example.block7CrudValidation.student.application;

import com.example.block7CrudValidation.asignatura.application.AsignaturaRepository;
import com.example.block7CrudValidation.asignatura.domain.Asignatura;
import com.example.block7CrudValidation.exceptions.EntityNotFoundException;
import com.example.block7CrudValidation.persona.application.PersonaRepository;
import com.example.block7CrudValidation.student.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Autowired
    AsignaturaRepository asignaturaRepository;

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(int id) {
        studentRepository.deleteById(String.valueOf(id));
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(int id) {
        return studentRepository.findById(String.valueOf(id)).orElse(null);
    }

    @Override
    public List<Student> readEveryStudent() {
        List<Student> estudiantes = studentRepository.findAll();
        return estudiantes;
    }

    @Override
    public Student getById(List<Student> estudiantes, int id) {
        for (Student estudiante : estudiantes) {
            if (estudiante.getId() == id) {
                return estudiante;
            }
        }
        return null;
    }

    @Override
    public boolean filterByID(List<Student> estudiantes, int id) {
        for (Student estudiante : estudiantes) {
            // Comparamos el ID del estudiante con el ID proporcionado
            if (estudiante.getId() == id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void assignAsignaturas(int studentId, List<Integer> asignaturaIds) throws EntityNotFoundException {
        Student student = getStudentById(studentId);
        List<Asignatura> asignaturas = asignaturaRepository.findAllById(asignaturaIds);

        // Validar si el estudiante y las asignaturas existen
        if (student == null || asignaturas.size() != asignaturaIds.size()) {
            throw new EntityNotFoundException("No se encontró el estudiante o alguna de las asignaturas.");
        }

        // Asignar las asignaturas al estudiante
        for (Asignatura asignatura : asignaturas) {
            asignatura.setStudent(student);
            asignaturaRepository.save(asignatura);
        }
    }

    @Override
    public void unassignAsignaturas(int studentId, List<Integer> asignaturaIds) throws EntityNotFoundException {
        Student student = getStudentById(studentId);

        // Validar si el estudiante existe
        if (student == null) {
            throw new EntityNotFoundException("No se encontró el estudiante.");
        }

        // Desasignar las asignaturas del estudiante
        for (Integer asignaturaId : asignaturaIds) {
            Asignatura asignatura = asignaturaRepository.findById(asignaturaId).orElse(null);
            if (asignatura != null && asignatura.getStudent() != null && asignatura.getStudent().getId_student() == studentId) {
                asignatura.setStudent(null);
                asignaturaRepository.save(asignatura);
            }
        }
    }
}
