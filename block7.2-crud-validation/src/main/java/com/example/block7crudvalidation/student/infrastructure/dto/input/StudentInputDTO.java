package com.example.block7crudvalidation.student.infrastructure.dto.input;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class StudentInputDTO {

    @NotNull(message = "Debe asignar los datos del estudiante.")
    private Integer idPerson;

    private Integer idTeacher;

    private int numHoursWeek;

    private String comments;

    @NotEmpty(message = "Debe asignar una asignatura al estudiante.")
    private String branch;

    private List<String> subjects = new ArrayList<>();

    private List<Integer> idSubjects = new ArrayList<>();
}