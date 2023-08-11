package com.example.block7crudvalidation.domain.subject;

import com.example.block7crudvalidation.subject.domain.Subject;

public class SubjectMother {

    public static Subject mockSubject(Integer idSubject, String name, String comments) {

        Subject subject = new Subject();
        subject.setIdSubject(idSubject);
        subject.setName(name);
        subject.setComments(comments);

        return subject;
    }
}