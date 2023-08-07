package com.example.block21SpringGraphQL.application.service;

import com.example.block21SpringGraphQL.models.Course;

import java.util.List;

public interface ICourseService {

    void createCourse(Course course);
    List<Course> findAll();
    Course findById(Long id);
    void deleteById(Long id);
}
