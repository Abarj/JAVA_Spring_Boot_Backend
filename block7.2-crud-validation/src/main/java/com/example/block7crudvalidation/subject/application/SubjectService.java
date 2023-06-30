package com.example.block7crudvalidation.subject.application;


import com.example.block7crudvalidation.subject.infrastructure.dto.input.SubjectInputDTO;
import com.example.block7crudvalidation.subject.infrastructure.dto.output.SubjectOutputDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface SubjectService {
    SubjectOutputDTO addSubject(SubjectInputDTO subjectInputDTO);
    SubjectOutputDTO getSubjectById(String id);
    List<SubjectOutputDTO> getSubjectStudentById(String idStudent);
    SubjectOutputDTO updateSubject(String id, SubjectInputDTO subjectInputDTO);
    void deleteSubject(String id);
}