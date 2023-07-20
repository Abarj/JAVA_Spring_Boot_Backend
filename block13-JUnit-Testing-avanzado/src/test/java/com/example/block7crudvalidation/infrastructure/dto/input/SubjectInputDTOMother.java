package com.example.block7crudvalidation.infrastructure.dto.input;

import com.example.block7crudvalidation.subject.infrastructure.dto.input.SubjectInputDTO;

public class SubjectInputDTOMother {

    public static SubjectInputDTO mockSubjectDTO() {

        SubjectInputDTO subjectInputDTO = new SubjectInputDTO();
        subjectInputDTO.setName("Historia");
        subjectInputDTO.setComments("Comentarios");

        return subjectInputDTO;
    }

}