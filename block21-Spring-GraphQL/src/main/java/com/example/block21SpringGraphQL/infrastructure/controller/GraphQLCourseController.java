package com.example.block21SpringGraphQL.infrastructure.controller;

import com.example.block21SpringGraphQL.application.service.ICourseService;
import com.example.block21SpringGraphQL.graphql.InputCourse;
import com.example.block21SpringGraphQL.models.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class GraphQLCourseController {

    @Autowired
    private ICourseService iCourseService;

    @QueryMapping(name = "findCourseById")
    public Course findById(@Argument(name = "courseId") String id) {
        Long courseId = Long.parseLong(id);
        return iCourseService.findById(courseId);
    }

    @QueryMapping(name = "findAllCourses")
    public List<Course> findAll() {
        return iCourseService.findAll();
    }

    @MutationMapping(name = "createCourse")
    public Course createCourse(@Argument(name = "inputCourse") InputCourse inputCourse) {

        Course course = new Course();
        course.setName(inputCourse.getName());
        course.setCategory(inputCourse.getCategory());
        course.setTeacher(inputCourse.getTeacher());

        iCourseService.createCourse(course);

        return course;
    }

    @MutationMapping(name = "deleteCourseById")
    public String deleteById(@Argument(name = "courseId") String id) {
        iCourseService.deleteById(Long.parseLong(id));

        return "The course with id " + id + " has been successfully deleted";
    }
}
