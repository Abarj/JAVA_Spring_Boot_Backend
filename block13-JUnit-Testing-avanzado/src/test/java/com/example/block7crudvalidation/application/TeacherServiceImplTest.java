package com.example.block7crudvalidation.application;

import com.example.block7crudvalidation.person.domain.Person;
import com.example.block7crudvalidation.person.infrastructure.repository.PersonRepository;
import com.example.block7crudvalidation.teacher.application.TeacherServiceImpl;
import com.example.block7crudvalidation.teacher.domain.Teacher;
import com.example.block7crudvalidation.teacher.infrastructure.dto.input.TeacherInputDTO;
import com.example.block7crudvalidation.teacher.infrastructure.dto.output.TeacherOutputDTO;
import com.example.block7crudvalidation.teacher.infrastructure.repository.TeacherRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TeacherServiceImplTest {

    @Mock
    private TeacherRepository teacherRepository;

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private TeacherServiceImpl teacherService;

    @Test
    public void testAddTeacher() {
        // Setup
        TeacherInputDTO teacherInputDTO = new TeacherInputDTO();
        teacherInputDTO.setIdPerson(1);

        Person person = new Person();
        person.setIdPerson(1);

        when(personRepository.findById(1)).thenReturn(Optional.of(person));

        Teacher teacher = new Teacher();
        teacher.setPerson(person);

        when(teacherRepository.save(any(Teacher.class))).thenAnswer(invocation -> {
            Teacher teacher1 = invocation.getArgument(0);
            teacher1.setIdTeacher(1); // Asignar el idTeacher deseado
            return teacher1;
        });

        // Execute
        TeacherOutputDTO result = teacherService.addTeacher(teacherInputDTO);

        // Verify
        assertNotNull(result);
        assertEquals(1, result.getIdTeacher());
        assertEquals(1, result.getIdPerson());
        assertNull(result.getBranch());

        verify(personRepository, times(1)).findById(1);
        verify(teacherRepository, times(1)).save(any(Teacher.class));
    }

    @Test
    public void testGetAllTeachers() {
        // Setup
        List<Teacher> teachers = new ArrayList<>();
        Teacher teacher1 = new Teacher();
        teacher1.setIdTeacher(1);
        Teacher teacher2 = new Teacher();
        teacher2.setIdTeacher(2);

        Person person1 = new Person();
        Person person2 = new Person();

        teacher1.setPerson(person1);
        teacher2.setPerson(person2);

        teachers.add(teacher1);
        teachers.add(teacher2);

        when(teacherRepository.findAll()).thenReturn(teachers);

        // Execute
        List<TeacherOutputDTO> result = teacherService.getAllTeachers("");

        // Verify
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getIdTeacher());
        assertEquals(2, result.get(1).getIdTeacher());

        verify(teacherRepository, times(1)).findAll();
    }

    @Test
    public void testGetTeacherId() throws Exception {
        // Setup
        Teacher teacher = new Teacher();
        teacher.setIdTeacher(1);

        Person person = new Person();
        person.setIdPerson(1);

        teacher.setPerson(person);

        when(teacherRepository.findById(1)).thenReturn(Optional.of(teacher));

        // Execute
        TeacherOutputDTO result = teacherService.getTeacherId(1, "");

        // Verify
        assertNotNull(result);
        assertEquals(1, result.getIdTeacher());

        verify(teacherRepository, times(1)).findById(1);
    }

    @Test
    public void testUpdateTeacher() {
        // Setup
        TeacherInputDTO teacherInputDTO = new TeacherInputDTO();
        teacherInputDTO.setIdPerson(1);
        teacherInputDTO.setComments("Profesor de historia");
        teacherInputDTO.setBranch("Historia");

        Person person = new Person();
        person.setIdPerson(1);
        when(personRepository.findById(1)).thenReturn(Optional.of(person));

        Teacher teacher = new Teacher();
        teacher.setIdTeacher(1);
        teacher.setPerson(person);


        when(teacherRepository.findById(1)).thenReturn(Optional.of(teacher));
        when(teacherRepository.save(any(Teacher.class))).thenReturn(teacher);

        // Execute
        TeacherOutputDTO result = teacherService.updateTeacher(1, teacherInputDTO);

        // Verify
        assertNotNull(result);
        assertEquals(1, result.getIdTeacher());
        assertEquals(1, result.getIdPerson());
        assertEquals("Profesor de historia", result.getComments());
        assertEquals("Historia", result.getBranch());

        verify(personRepository, times(1)).findById(1);
        verify(teacherRepository, times(1)).findById(1);
        verify(teacherRepository, times(1)).save(any(Teacher.class));
    }

    @Test
    public void testDeleteTeacher() {
        // Setup
        Teacher teacher = new Teacher();
        teacher.setIdTeacher(1);

        Person person = new Person();
        person.setIdPerson(1);

        teacher.setPerson(person);

        // Execute
        teacherService.deleteTeacher(1);

        // Verify
        verify(teacherRepository, times(1)).deleteById(1);
    }
}
