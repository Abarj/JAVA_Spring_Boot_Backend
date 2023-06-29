package com.example.block7CrudValidation.asignatura.application;

import com.example.block7CrudValidation.asignatura.domain.Asignatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AsignaturaRepository extends JpaRepository<Asignatura, Integer> {
    List<Asignatura> findByStudentId(int id_student);
}

