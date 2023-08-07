package com.example.block21SpringGraphQL.infrastructure.controller;

import com.example.block21SpringGraphQL.application.service.ICourseService;
import com.example.block21SpringGraphQL.application.service.IStudentService;
import com.example.block21SpringGraphQL.graphql.InputStudent;
import com.example.block21SpringGraphQL.models.Course;
import com.example.block21SpringGraphQL.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class GraphQLStudentController {

    @Autowired
    private IStudentService iStudentService;

    @Autowired
    private ICourseService iCourseService;

    @MutationMapping(name = "createStudent")
    public Student createStudent(@Argument(name = "inputStudent") InputStudent inputStudent) {

        Course course = iCourseService.findById(Long.parseLong(inputStudent.getCourseId()));

        Student student = new Student();
        student.setName(inputStudent.getName());
        student.setLastName(inputStudent.getLastName());
        student.setAge(inputStudent.getAge());
        student.setCourse(course);

        iStudentService.createStudent(student);

        return student;
    }

    @QueryMapping(name = "findStudentById") // Mapear la función de GraphQL con este método + Mapear los argumentos con @Argument
    public Student findById(@Argument(name = "studentId") String id) { // Lo recibimos con String a través de GraphQL y lo parseamos a Long para consumir la capa de servicio
        Long studentId = Long.parseLong(id);
        return iStudentService.findById(studentId);
    }
    @QueryMapping(name = "findAllStudents")
    public List<Student> findAll() {
        return iStudentService.findAll();
    }

    @MutationMapping(name = "deleteStudentById")
    public String deleteById(@Argument(name = "studentId") String id) {
        iStudentService.deleteById(Long.parseLong(id));

        return "The student with id " + id + " has been successfully deleted";
    }
}
