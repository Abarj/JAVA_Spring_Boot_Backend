package com.example.block7crudvalidation.subject.infrastructure.controller;

import com.example.block7crudvalidation.subject.application.SubjectService;
import com.example.block7crudvalidation.subject.infrastructure.dto.input.SubjectInputDTO;
import com.example.block7crudvalidation.subject.infrastructure.dto.output.SubjectOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    SubjectService subjectService;

    @PostMapping
    public SubjectOutputDTO addSubject(@Valid @RequestBody SubjectInputDTO subjectInputDTO) {
        return subjectService.addSubject(subjectInputDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteSubject(@PathVariable("id") String id) {
        subjectService.deleteSubject(id);
    }

    @GetMapping("/{id}")
    public SubjectOutputDTO getSubject(@PathVariable("id") String id) {
        return subjectService.getSubjectById(id);
    }

    @GetMapping("/subjects/{id}")
    public List<SubjectOutputDTO> getSubjectStudentById(@PathVariable("id") String idStudent) {
        return subjectService.getSubjectStudentById(idStudent);
    }

    @PutMapping("/{id}")
    public SubjectOutputDTO updateSubject(@PathVariable("id") String id,
                                        @RequestBody SubjectInputDTO subjectInputDTO) {
        return subjectService.updateSubject(id, subjectInputDTO);
    }
}