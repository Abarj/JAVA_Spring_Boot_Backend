package com.example.block7crudvalidation.subject.infrastructure.dto.input;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
public class SubjectInputDTO {

    @NotNull(message = "Debe asignar un profesor a la asignatura.")
    private Integer idTeacher;

    @NotNull(message = "Debe asignar un nombre a la asignatura.")
    private String name;

    private String comments;

    private Date initialDate;

    private Date finishDate;

    private List<Integer> idsStudents = new ArrayList<>();
}
