package com.example.block7crudvalidation.subject.application;


import com.example.block7crudvalidation.subject.infrastructure.dto.input.SubjectInputDTO;
import com.example.block7crudvalidation.subject.infrastructure.dto.output.SubjectOutputDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SubjectService {
    SubjectOutputDTO addSubject(SubjectInputDTO subjectInputDTO);
    SubjectOutputDTO getSubjectById(Integer id);
    List<SubjectOutputDTO> getSubjectStudentById(Integer idStudent);
    SubjectOutputDTO updateSubject(Integer id, SubjectInputDTO subjectInputDTO);
    void deleteSubject(Integer id);
}