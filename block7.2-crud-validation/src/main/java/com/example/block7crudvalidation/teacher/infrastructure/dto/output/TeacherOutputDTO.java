package com.example.block7crudvalidation.teacher.infrastructure.dto.output;

import com.example.block7crudvalidation.teacher.domain.Teacher;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherOutputDTO {

    private Integer idTeacher;
    private Integer idPerson;
    private String comments;
    private String branch;

    public TeacherOutputDTO(Teacher teacher) {
        setIdTeacher(teacher.getIdTeacher());
        setIdPerson(teacher.getPerson().getIdPerson());
        setComments(teacher.getComments());
        setBranch(teacher.getBranch());
    }
}