package com.example.block7crudvalidation.teacher.application;

import com.example.block7crudvalidation.person.domain.Person;
import com.example.block7crudvalidation.person.infrastructure.repository.PersonRepository;
import com.example.block7crudvalidation.teacher.domain.Teacher;
import com.example.block7crudvalidation.teacher.infrastructure.dto.input.TeacherInputDTO;
import com.example.block7crudvalidation.teacher.infrastructure.dto.output.TeacherFullOutputDTO;
import com.example.block7crudvalidation.teacher.infrastructure.dto.output.TeacherOutputDTO;
import com.example.block7crudvalidation.teacher.infrastructure.repository.TeacherRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    PersonRepository personRepository;

    @Override
    public TeacherOutputDTO addTeacher(TeacherInputDTO teacherInputDTO) {
        Person person = personRepository.findById(teacherInputDTO.getIdPerson())
                .orElseThrow(() -> new RuntimeException("No person found with id: " + teacherInputDTO.getIdPerson()));
        Teacher teacher = new Teacher(teacherInputDTO);
        teacher.setPerson(person);
        teacherRepository.save(teacher);

        return new TeacherOutputDTO(teacher);
    }

    @Override
    public List<TeacherOutputDTO> getAllTeachers(String outputType) {
        List<Teacher> teachers = teacherRepository.findAll();
        List<TeacherOutputDTO> teacherOutputDTOs = new ArrayList<>();

        for (Teacher teacher : teachers) {
            if ("full".equals(outputType)) {
                teacherOutputDTOs.add(new TeacherFullOutputDTO(teacher));
            } else {
                teacherOutputDTOs.add(new TeacherOutputDTO(teacher));
            }
        }

        return teacherOutputDTOs;
    }

    @Override
    public TeacherOutputDTO getTeacherId(Integer id, String outputType) throws Exception {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se ha encontrado ningún profesor con el id: " + id));

        if ("full".equals(outputType)) {
            return new TeacherFullOutputDTO(teacher);
        } else {
            return new TeacherOutputDTO(teacher);
        }
    }

    @Override
    public TeacherOutputDTO updateTeacher(Integer id, TeacherInputDTO teacherInputDTO) {
        Teacher foundTeacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No teacher found with id: " + id));
        Person person = personRepository.findById(teacherInputDTO.getIdPerson())
                .orElseThrow(() -> new RuntimeException("No person found with id: " + id));
        foundTeacher.update(teacherInputDTO);
        foundTeacher.setPerson(person);
        teacherRepository.save(foundTeacher);

        return new TeacherOutputDTO(foundTeacher);
    }

    @Override
    public void deleteTeacher(Integer id) {
        teacherRepository.deleteById(id);
    }
}
