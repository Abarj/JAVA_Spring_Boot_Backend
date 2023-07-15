package com.example.block7crudvalidation.person.infrastructure.dto.output;

import com.example.block7crudvalidation.person.domain.Person;
import com.example.block7crudvalidation.student.infrastructure.dto.output.StudentOutputDTO;
public class PersonStudentDTO extends PersonOutputDTO {

    public StudentOutputDTO student;

    public PersonStudentDTO(Person person) {
        super(person);
    }

    public void setStudent(StudentOutputDTO studentOutputDTO) {
        this.student = studentOutputDTO;
    }
}