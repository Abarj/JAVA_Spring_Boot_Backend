package com.example.block7crudvalidation.domain.student.domain;

import com.example.block7crudvalidation.student.domain.Student;

public class StudentMother {

    public static Student mockStudent(Integer idStudent, int numHoursWeek, String comments, String branch) {

        Student student = new Student();
        student.setIdStudent(idStudent);
        student.setNumHoursWeek(numHoursWeek);
        student.setComments(comments);
        student.setBranch(branch);

        return student;
    }
}
