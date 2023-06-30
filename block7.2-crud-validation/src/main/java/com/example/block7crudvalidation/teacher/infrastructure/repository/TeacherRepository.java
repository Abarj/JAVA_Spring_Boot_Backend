package com.example.block7crudvalidation.teacher.infrastructure.repository;

import com.example.block7crudvalidation.teacher.domain.Teacher;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, String> {
}