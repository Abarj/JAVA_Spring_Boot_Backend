package com.example.block7crudvalidation.student.domain;

import com.example.block7crudvalidation.student.infrastructure.dto.input.StudentInputDTO;
import com.example.block7crudvalidation.person.domain.Person;
import com.example.block7crudvalidation.subject.domain.Subject;
import com.example.block7crudvalidation.teacher.domain.Teacher;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer idStudent;

    @OneToOne
    @JoinColumn(name = "id_person")
    private Person person;

    @Column
    private int numHoursWeek;

    @Column
    private String comments;

    @ManyToOne
    @JoinColumn(name = "idTeacher")
    Teacher teacher;

    @Column
    String branch;

    @OneToMany
    private List<Subject> subjects;

    public Student(StudentInputDTO studentInputDTO) {
        setComments(studentInputDTO.getComments());
    }

    public void update(StudentInputDTO studentInputDTO) {
        setNumHoursWeek(studentInputDTO.getNumHoursWeek());
        setComments(studentInputDTO.getComments());
        setBranch(studentInputDTO.getBranch());
    }
}