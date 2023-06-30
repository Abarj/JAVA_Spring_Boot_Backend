package com.example.block7crudvalidation.teacher.infrastructure.dto.output;

import com.example.block7crudvalidation.teacher.domain.Teacher;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TeacherOutputDTO {

    private String idTeacher;
    private String idPerson;
    private String comments;
    private String branch;

    public TeacherOutputDTO(Teacher teacher) {
        setIdTeacher(teacher.getIdTeacher());
        setIdPerson(teacher.getPerson().getIdPerson());
        setComments(teacher.getComments());
        setBranch(teacher.getBranch());
    }
}