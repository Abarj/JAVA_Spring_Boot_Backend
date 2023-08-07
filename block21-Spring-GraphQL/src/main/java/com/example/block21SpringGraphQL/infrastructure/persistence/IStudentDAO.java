package com.example.block21SpringGraphQL.infrastructure.persistence;

import com.example.block21SpringGraphQL.models.Student;
import org.springframework.data.repository.CrudRepository;

public interface IStudentDAO extends CrudRepository<Student, Long> {
}
