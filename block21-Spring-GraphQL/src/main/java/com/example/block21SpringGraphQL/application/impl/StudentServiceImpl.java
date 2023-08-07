package com.example.block21SpringGraphQL.application.impl;

import com.example.block21SpringGraphQL.application.service.IStudentService;
import com.example.block21SpringGraphQL.infrastructure.persistence.IStudentDAO;
import com.example.block21SpringGraphQL.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private IStudentDAO iStudentDAO;

    @Override
    @Transactional
    public void createStudent(Student student) {
        iStudentDAO.save(student);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> findAll() {
        return (List<Student>) iStudentDAO.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Student findById(Long id) {
        return iStudentDAO.findById(id)
                .orElseThrow();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        iStudentDAO.deleteById(id);
    }
}
