<<<<<<< HEAD
package com.example.block7CrudValidation.estudiante_asignatura.application;

import com.example.block7CrudValidation.estudiante_asignatura.domain.EstudianteAsignatura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstudianteAsignaturaRepository extends JpaRepository<EstudianteAsignatura, Integer> {
    List<EstudianteAsignatura> findByStudentId(int id_student);
}
=======
package com.example.block7CrudValidation.estudiante_asignatura.application;

import com.example.block7CrudValidation.estudiante_asignatura.domain.EstudianteAsignatura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstudianteAsignaturaRepository extends JpaRepository<EstudianteAsignatura, String> {
    List<EstudianteAsignatura> findByStudentId(String idStudent);
}
>>>>>>> 0215ed2c784a238139adc681a90ff64f94587231
