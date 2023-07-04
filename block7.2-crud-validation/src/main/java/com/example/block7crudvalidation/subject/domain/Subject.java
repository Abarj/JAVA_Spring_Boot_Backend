package com.example.block7crudvalidation.subject.domain;

import com.example.block7crudvalidation.student.infrastructure.dto.input.StudentInputDTO;
import com.example.block7crudvalidation.teacher.domain.Teacher;
import com.example.block7crudvalidation.student.domain.Student;
import com.example.block7crudvalidation.subject.infrastructure.dto.input.SubjectInputDTO;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Subjects")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer idSubject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_teacher")
    private Teacher teacher;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "subject")
    private List<Student> students = new ArrayList<>();

    @Column
    private String name;

    @Column
    private String comments;

    @Column
    private Date initialDate;

    @Column
    private Date finishDate;


    public void update(SubjectInputDTO subjectInputDTO) {
        if (subjectInputDTO.getName() != null) {
            setName(subjectInputDTO.getName());
        }
        if (subjectInputDTO.getInitialDate() != null) {
            setInitialDate(subjectInputDTO.getInitialDate());
        }
        if (subjectInputDTO.getFinishDate() != null) {
            setFinishDate(subjectInputDTO.getFinishDate());
        }
    }

    public Subject(SubjectInputDTO subjectInputDTO) {
        setComments(subjectInputDTO.getComments());
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void deleteStudent(Student student) {
        students.remove(student);
    }
}