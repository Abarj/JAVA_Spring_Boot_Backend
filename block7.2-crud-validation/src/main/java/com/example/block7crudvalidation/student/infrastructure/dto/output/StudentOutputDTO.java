package com.example.block7crudvalidation.student.infrastructure.dto.output;

import com.example.block7crudvalidation.subject.domain.Subject;
import com.example.block7crudvalidation.student.domain.Student;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class StudentOutputDTO {

    private String idStudent;
    private String idPerson;
    private String idTeacher;
    private int numHoursWeek;
    private String comments;
    private String branch;
    private List<String> subjects = new ArrayList<>();

    public StudentOutputDTO(Student student) {
        setIdStudent(student.getIdStudent());
        setIdPerson(student.getPerson().getIdPerson());
        setIdTeacher(student.getTeacher().getIdTeacher());
        setNumHoursWeek(student.getNumHoursWeek());
        setComments(student.getComments());
        setBranch(student.getBranch());
        setSubjects(student.getSubjects().stream().map(Subject::getIdSubject).toList());
    }
}