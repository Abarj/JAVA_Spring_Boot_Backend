package com.example.block7crudvalidation.student.infrastructure.repository;

import com.example.block7crudvalidation.student.domain.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
}