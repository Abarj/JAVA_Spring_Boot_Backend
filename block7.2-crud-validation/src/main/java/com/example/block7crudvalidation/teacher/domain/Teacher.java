package com.example.block7crudvalidation.teacher.domain;

import com.example.block7crudvalidation.subject.domain.Subject;
import com.example.block7crudvalidation.student.domain.Student;
import com.example.block7crudvalidation.person.domain.Person;
import com.example.block7crudvalidation.teacher.infrastructure.dto.input.TeacherInputDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "Teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String idTeacher;

    @OneToOne
    @JoinColumn(name = "id_person")
    private Person person;

    @Column
    private String comments;

    @Column
    private String branch;

    @OneToMany
    private List<Subject> subjects;

    @OneToMany
    private List<Student> students;

    public Teacher(TeacherInputDTO teacherInputDTO) {
        setComments(teacherInputDTO.getComments());
        setBranch(teacherInputDTO.getBranch());
    }

    public void update(TeacherInputDTO teacherInputDTO) {
        if (teacherInputDTO.getComments() != null) {
            setComments(teacherInputDTO.getComments());
        }
        if (teacherInputDTO.getBranch() != null) {
            setBranch(teacherInputDTO.getBranch());
        }
    }
}