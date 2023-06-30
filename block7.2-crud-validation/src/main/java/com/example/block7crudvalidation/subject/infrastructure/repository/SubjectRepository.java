package com.example.block7crudvalidation.subject.infrastructure.repository;

import com.example.block7crudvalidation.subject.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, String> {
}