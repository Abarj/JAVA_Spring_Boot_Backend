package com.example.block7crudvalidation.subject.infrastructure.dto.output;

import com.example.block7crudvalidation.subject.domain.Subject;
import com.example.block7crudvalidation.student.domain.Student;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
public class SubjectOutputDTO {

    private int idSubject;
    private int idTeacher;
    private String name;
    private String comments;
    private Date initialDate;
    private Date finishDate;
    private List<Integer> idsStudents = new ArrayList<>();

    public SubjectOutputDTO(Subject subject) {
        setIdSubject(subject.getIdSubject());
        setName(subject.getName());
        setComments(subject.getComments());
        setInitialDate(subject.getInitialDate());
        setFinishDate(subject.getFinishDate());
        setIdsStudents(subject.getStudents().stream().map(Student::getIdStudent).collect(Collectors.toList()));
    }
}