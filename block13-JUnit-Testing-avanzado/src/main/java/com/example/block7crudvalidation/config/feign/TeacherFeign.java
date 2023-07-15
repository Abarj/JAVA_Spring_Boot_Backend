package com.example.block7crudvalidation.config.feign;

import com.example.block7crudvalidation.teacher.infrastructure.dto.output.TeacherOutputDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "teacherFeign", url = "http://localhost:8081")
public interface TeacherFeign {
    @GetMapping("/teacher/{id}")
    TeacherOutputDTO getTeacher(@PathVariable("id") Integer id);
}
