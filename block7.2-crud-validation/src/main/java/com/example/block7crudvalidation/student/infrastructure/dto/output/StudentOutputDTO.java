package com.example.block7crudvalidation.student.infrastructure.dto.output;

import com.example.block7crudvalidation.subject.domain.Subject;
import com.example.block7crudvalidation.student.domain.Student;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentOutputDTO {

    private Integer idStudent;
    private Integer idPerson;
    private Integer idTeacher;
    private int numHoursWeek;
    private String comments;
    private String branch;
    private List<Integer> subjects = new ArrayList<>();

    public StudentOutputDTO(Student student) {
        this.idStudent = student.getIdStudent();
        this.idPerson = student.getPerson().getIdPerson();
        this.idTeacher = student.getTeacher().getIdTeacher();
        this.numHoursWeek = student.getNumHoursWeek();
        this.comments = student.getComments();
        this.branch = student.getBranch();
        this.subjects = student.getSubjects().stream()
                .map(Subject::getIdSubject)
                .collect(Collectors.toList());
    }
}