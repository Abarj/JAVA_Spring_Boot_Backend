package com.example.block7crudvalidation.student.domain;

import com.example.block7crudvalidation.student.infrastructure.dto.input.StudentInputDTO;
import com.example.block7crudvalidation.person.domain.Person;
import com.example.block7crudvalidation.subject.domain.Subject;
import com.example.block7crudvalidation.teacher.domain.Teacher;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "Students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String idStudent;

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
        setNumHoursWeek(studentInputDTO.getNumHoursWeek());
        setComments(studentInputDTO.getComments());
        setBranch(studentInputDTO.getBranch());
    }

    public void update(StudentInputDTO studentInputDTO) {
        setNumHoursWeek(studentInputDTO.getNumHoursWeek());
        setComments(studentInputDTO.getComments());
        setBranch(studentInputDTO.getBranch());
    }
}