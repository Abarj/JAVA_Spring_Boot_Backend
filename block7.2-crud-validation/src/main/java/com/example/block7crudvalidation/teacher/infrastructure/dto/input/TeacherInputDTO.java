package com.example.block7crudvalidation.teacher.infrastructure.dto.input;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class TeacherInputDTO {

    @NotEmpty(message = "Teacher data cannot be empty.")
    private String idPerson;

    private String comments;

    @NotEmpty(message = "The teacher must belong to a branch.")
    private String branch;
}