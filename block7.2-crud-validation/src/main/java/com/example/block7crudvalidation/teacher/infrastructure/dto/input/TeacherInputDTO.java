package com.example.block7crudvalidation.teacher.infrastructure.dto.input;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Setter
@Getter
public class TeacherInputDTO {

    @NotEmpty(message = "Teacher data cannot be empty.")
    private Integer idPerson;

    private String comments;

    @NotEmpty(message = "The teacher must belong to a branch.")
    private String branch;
}