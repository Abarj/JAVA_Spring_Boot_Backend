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

import javax.ws.rs.NotFoundException;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public TeacherOutputDTO addTeacher(TeacherInputDTO teacherInputDTO) {
        Person person = personRepository.findById(teacherInputDTO.getIdPerson())
                .orElseThrow(() -> new NotFoundException("No person found with id: " + teacherInputDTO.getIdPerson()));
        Teacher teacher = new Teacher(teacherInputDTO);
        teacher.setPerson(person);
        teacherRepository.save(teacher);

        return new TeacherOutputDTO(teacher);
    }

    @Override
    public TeacherOutputDTO getTeacherId(String id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No teacher found with id: " + id));

        return new TeacherOutputDTO(teacher);
    }

    @Override
    public ResponseEntity<TeacherOutputDTO> getTeacherId(String id, String outputType) throws Exception {
        return null;
    }

    public TeacherFullOutputDTO findTeacherId(String id, String outputType) throws Exception {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if (!teacher.isPresent()) {
            throw new NotFoundException("No teacher found with id: " + id);
        }
        if (outputType.equals("full")) {
            return modelMapper.map(teacher, TeacherFullOutputDTO.class);
        } else {
            return (TeacherFullOutputDTO) modelMapper.map(teacher, TeacherOutputDTO.class);
        }
    }

    @Override
    public TeacherOutputDTO updateTeacher(String id, TeacherInputDTO teacherInputDTO) {
        Teacher foundTeacher = teacherRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No teacher found with id: " + id));
        Person person = personRepository.findById(teacherInputDTO.getIdPerson())
                .orElseThrow(() -> new NotFoundException("No person found with id: " + id));
        foundTeacher.update(teacherInputDTO);
        foundTeacher.setPerson(person);
        teacherRepository.save(foundTeacher);

        return new TeacherOutputDTO(foundTeacher);
    }

    @Override
    public void deleteTeacher(String id) {
        teacherRepository.deleteById(id);
    }
}
