package com.example.block7crudvalidation.domain.teacher;

import com.example.block7crudvalidation.teacher.domain.Teacher;

public class TeacherMother {

    public static Teacher mockTeacher(Integer idTeacher, String comments, String branch) {

        Teacher teacher = new Teacher();
        teacher.setIdTeacher(idTeacher);
        teacher.setComments(comments);
        teacher.setBranch(branch);

        return teacher;

    }
}