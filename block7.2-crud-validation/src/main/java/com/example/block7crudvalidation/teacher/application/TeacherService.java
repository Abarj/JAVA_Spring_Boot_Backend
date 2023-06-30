package com.example.block7crudvalidation.teacher.application;

import com.example.block7crudvalidation.teacher.infrastructure.dto.input.TeacherInputDTO;
import com.example.block7crudvalidation.teacher.infrastructure.dto.output.TeacherOutputDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

public interface TeacherService {
    TeacherOutputDTO addTeacher(TeacherInputDTO teacherInputDTO);
    TeacherOutputDTO getTeacherId(String id);
    ResponseEntity<TeacherOutputDTO> getTeacherId(String id, String outputType) throws Exception;
    TeacherOutputDTO updateTeacher(String id, TeacherInputDTO teacherInputDTO);
    void deleteTeacher(String id);
}