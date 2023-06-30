package com.example.block7crudvalidation.teacher.infrastructure.controller;

import com.example.block7crudvalidation.teacher.application.TeacherService;
import com.example.block7crudvalidation.teacher.infrastructure.dto.input.TeacherInputDTO;
import com.example.block7crudvalidation.teacher.infrastructure.dto.output.TeacherOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @PostMapping
    public TeacherOutputDTO addTeacher(@RequestBody TeacherInputDTO teacherInputDTO) {
        return teacherService.addTeacher(teacherInputDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherOutputDTO> getTeacherId(@PathVariable("id") String id,
                                                         @QueryParam("outputType") String outputType) throws Exception {
        return teacherService.getTeacherId(id, outputType);
    }

    @PutMapping("/{id}")
    public TeacherOutputDTO updateTeacher(@PathVariable("id") String id, @RequestBody TeacherInputDTO teacherInputDTO) {
        return teacherService.updateTeacher(id, teacherInputDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteTeacher(@PathVariable("id") String id) {
        teacherService.deleteTeacher(id);
    }
}