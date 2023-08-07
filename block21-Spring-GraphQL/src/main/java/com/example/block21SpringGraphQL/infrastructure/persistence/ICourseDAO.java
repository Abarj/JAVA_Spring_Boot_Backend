package com.example.block21SpringGraphQL.infrastructure.persistence;

import com.example.block21SpringGraphQL.models.Course;
import org.springframework.data.repository.CrudRepository;

public interface ICourseDAO extends CrudRepository<Course, Long> {
}
