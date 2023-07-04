package com.example.block7crudvalidation.teacher.infrastructure.repository;

import com.example.block7crudvalidation.teacher.domain.Teacher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
}