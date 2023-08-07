package com.example.block21SpringGraphQL.application.impl;

import com.example.block21SpringGraphQL.application.service.ICourseService;
import com.example.block21SpringGraphQL.infrastructure.persistence.ICourseDAO;
import com.example.block21SpringGraphQL.models.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseServiceImpl implements ICourseService {

    @Autowired
    private ICourseDAO iCourseDAO;

    @Override
    @Transactional
    public void createCourse(Course course) {
        iCourseDAO.save(course);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Course> findAll() {
        return (List<Course>) iCourseDAO.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Course findById(Long id) {
        return iCourseDAO.findById(id)
                .orElseThrow();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        iCourseDAO.deleteById(id);
    }
}
