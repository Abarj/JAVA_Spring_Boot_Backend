package com.example.block7crudvalidation.infrastructure.dto.input;

import com.example.block7crudvalidation.teacher.infrastructure.dto.input.TeacherInputDTO;

public class TeacherInputDTOMother {

    public static TeacherInputDTO mochkStudentDTO() {

        TeacherInputDTO teacherInputDTO = new TeacherInputDTO();
        teacherInputDTO.setIdPerson(1);
        teacherInputDTO.setComments("Comentarios");
        teacherInputDTO.setBranch("Rama");

        return teacherInputDTO;
    }
}