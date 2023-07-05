package com.example.block7crudvalidation.teacher.infrastructure.controller;

import com.example.block7crudvalidation.teacher.application.TeacherService;
import com.example.block7crudvalidation.teacher.infrastructure.dto.input.TeacherInputDTO;
import com.example.block7crudvalidation.teacher.infrastructure.dto.output.TeacherOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @PostMapping
    public TeacherOutputDTO addTeacher(@RequestBody TeacherInputDTO teacherInputDTO) {
        return teacherService.addTeacher(teacherInputDTO);
    }

    @GetMapping
    public List<TeacherOutputDTO> getAllTeachers(@RequestParam(name = "outputType", defaultValue = "simple") String outputType) {
        return teacherService.getAllTeachers(outputType);
    }

    @GetMapping("/{id}")
    public TeacherOutputDTO getTeacherId(@PathVariable("id") Integer id,
                                         @RequestParam(name = "outputType", defaultValue = "simple") String outputType) throws Exception {
        return teacherService.getTeacherId(id, outputType);
    }

    @PutMapping("/{id}")
    public TeacherOutputDTO updateTeacher(@PathVariable("id") Integer id, @RequestBody TeacherInputDTO teacherInputDTO) {
        return teacherService.updateTeacher(id, teacherInputDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteTeacher(@PathVariable("id") Integer id) {
        teacherService.deleteTeacher(id);
    }
}