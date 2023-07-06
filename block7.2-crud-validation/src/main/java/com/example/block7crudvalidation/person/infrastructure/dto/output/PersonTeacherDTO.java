package com.example.block7crudvalidation.person.infrastructure.dto.output;

import com.example.block7crudvalidation.person.domain.Person;
import com.example.block7crudvalidation.teacher.infrastructure.dto.output.TeacherOutputDTO;

public class PersonTeacherDTO extends PersonOutputDTO {

    public TeacherOutputDTO teacher;

    public PersonTeacherDTO(Person person) {
        super(person);
    }

    public void setTeacher(TeacherOutputDTO teacherOutputDTO) {
        this.teacher = teacherOutputDTO;
    }
}