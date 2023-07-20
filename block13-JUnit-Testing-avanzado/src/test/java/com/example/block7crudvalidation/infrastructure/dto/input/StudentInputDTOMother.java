package com.example.block7crudvalidation.infrastructure.dto.input;

import com.example.block7crudvalidation.student.infrastructure.dto.input.StudentInputDTO;

public class StudentInputDTOMother {

    public static StudentInputDTO mockStudentDTO() {

        StudentInputDTO studentInputDTO = new StudentInputDTO();
        studentInputDTO.setIdPerson(1);
        studentInputDTO.setNumHoursWeek(20);
        studentInputDTO.setComments("Comentarios");
        studentInputDTO.setBranch("Rama");

        return studentInputDTO;
    }
}