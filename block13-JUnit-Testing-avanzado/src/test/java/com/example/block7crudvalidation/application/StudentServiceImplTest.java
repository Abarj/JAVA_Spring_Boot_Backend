package com.example.block7crudvalidation.application;

import com.example.block7crudvalidation.domain.subject.domain.SubjectMother;
import com.example.block7crudvalidation.domain.teacher.domain.TeacherMother;
import com.example.block7crudvalidation.person.domain.Person;
import com.example.block7crudvalidation.domain.person.domain.PersonMother;
import com.example.block7crudvalidation.person.infrastructure.repository.PersonRepository;
import com.example.block7crudvalidation.student.application.StudentServiceImpl;
import com.example.block7crudvalidation.student.domain.Student;
import com.example.block7crudvalidation.student.infrastructure.dto.input.StudentInputDTO;
import com.example.block7crudvalidation.infrastructure.dto.input.StudentInputDTOMother;
import com.example.block7crudvalidation.student.infrastructure.dto.output.StudentOutputDTO;
import com.example.block7crudvalidation.student.infrastructure.repository.StudentRepository;
import com.example.block7crudvalidation.subject.domain.Subject;
import com.example.block7crudvalidation.subject.infrastructure.repository.SubjectRepository;
import com.example.block7crudvalidation.teacher.domain.Teacher;
import com.example.block7crudvalidation.teacher.infrastructure.repository.TeacherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private TeacherRepository teacherRepository;

    @Mock
    private SubjectRepository subjectRepository;

    @InjectMocks
    private StudentServiceImpl studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddStudent() {
        // Setup
        StudentInputDTO studentInputDTO = StudentInputDTOMother.mockStudentDTO();
        Person person = PersonMother.mockPerson(1, "abarj", "Alvaro", "alvaro@email.com");
        Teacher teacher = TeacherMother.mockTeacher(1, "Comentarios", "Rama");

        studentInputDTO.setIdPerson(1);
        studentInputDTO.setIdTeacher(1);

        Subject subject1 = SubjectMother.mockSubject(1, "Inglés", "Comentarios");
        Subject subject2 = SubjectMother.mockSubject(2, "Historia", "Comentarios");
        Subject subject3 = SubjectMother.mockSubject(3, "Ciencias", "Comentarios");

        List<Integer> subjectIds = new ArrayList<>();
        subjectIds.add(1);
        subjectIds.add(2);
        subjectIds.add(3);

        List<Subject> subjects = new ArrayList<>();
        subjects.add(subject1);
        subjects.add(subject2);
        subjects.add(subject3);

        when(personRepository.findById(1)).thenReturn(Optional.of(person));
        when(teacherRepository.findById(1)).thenReturn(Optional.of(teacher));
        //when(subjectRepository.findAllById(anyList())).thenReturn(subjects);

        // Execute
        StudentOutputDTO result = studentService.addStudent(studentInputDTO);

        // Verify
        //assertEquals(1, result.getIdStudent());
        assertEquals(1, result.getIdPerson());
        assertEquals(1, result.getIdTeacher());
        //assertEquals(10, result.getNumHoursWeek());
        assertEquals("Comentarios", result.getComments());
        //assertEquals("Rama", result.getBranch());
        //assertEquals(subjectIds, result.getSubjects());

        verify(personRepository, times(1)).findById(1);
        verify(teacherRepository, times(1)).findById(1);
        //verify(subjectRepository, times(1)).findAllById(anyList());
        verify(studentRepository, times(1)).save(any(Student.class));
    }

    @Test
    void testGetAllStudents() {
        // Setup
        List<Student> students = new ArrayList<>();
        Student student1 = new Student();
        student1.setIdStudent(1);
        Student student2 = new Student();
        student2.setIdStudent(2);

        Person person1 = new Person();
        Person person2 = new Person();

        student1.setPerson(person1);
        student2.setPerson(person2);

        Subject subject1 = new Subject();
        Subject subject2 = new Subject();
        subject1.setIdSubject(1);
        subject2.setIdSubject(2);

        List<Subject> subjects = new ArrayList<>();
        subjects.add(subject1);
        subjects.add(subject2);

        student1.setSubjects(subjects);
        student2.setSubjects(subjects);

        students.add(student1);
        students.add(student2);

        when(studentRepository.findAll()).thenReturn(students);

        // Execute
        List<StudentOutputDTO> result = studentService.getAllStudents("");

        // Verify
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getIdStudent());
        assertEquals(2, result.get(1).getIdStudent());

        verify(studentRepository, times(1)).findAll();
    }

    @Test
    void testAddSubjects() {
        // Setup
        Student student = new Student();
        student.setIdStudent(1);

        Person person1 = new Person();
        student.setPerson(person1);

        Subject subject1 = new Subject();
        Subject subject2 = new Subject();
        subject1.setIdSubject(1);
        subject2.setIdSubject(2);

        List<Subject> emptySubjects = new ArrayList<>();
        student.setSubjects(emptySubjects);

        List<Subject> subjects = new ArrayList<>();
        subjects.add(subject1);
        subjects.add(subject2);

        when(studentRepository.findById(1)).thenReturn(Optional.of(student));

        when(subjectRepository.findAllById(Arrays.asList(1, 2))).thenReturn(Arrays.asList(subject1, subject2));

        // Execute
        StudentOutputDTO result = studentService.addSubjects(1, Arrays.asList(1, 2));

        // Verify
        assertEquals(1, result.getIdStudent());
        assertEquals(Arrays.asList(1, 2), result.getSubjects());

        verify(studentRepository, times(1)).findById(1);
        verify(subjectRepository, times(1)).findAllById(Arrays.asList(1, 2));
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    void testGetStudentId() {
        // Setup
        Student student = new Student();
        student.setIdStudent(1);

        when(studentRepository.findById(1)).thenReturn(Optional.of(student));

        // Execute
        Student result = studentService.getStudentId(1);

        // Verify
        assertEquals(student, result);

        verify(studentRepository, times(1)).findById(1);
    }

    @Test
    void testUpdateStudent() {
        // Setup
        Student student = new Student();
        student.setIdStudent(1);

        Subject subject1 = new Subject();
        Subject subject2 = new Subject();
        subject1.setIdSubject(1);
        subject2.setIdSubject(2);

        List<Subject> subjects = new ArrayList<>();

        student.setSubjects(subjects);

        when(studentRepository.findById(1)).thenReturn(Optional.of(student));

        StudentInputDTO studentInputDTO = new StudentInputDTO();
        studentInputDTO.setIdPerson(1);
        studentInputDTO.setComments("Aprobado");
        studentInputDTO.setBranch("Historia");

        Person person = new Person();
        person.setIdPerson(1);

        when(personRepository.findById(1)).thenReturn(Optional.of(person));

        when(studentRepository.save(any(Student.class))).thenReturn(student);

        // Execute
        StudentOutputDTO result = studentService.updateStudent(1, studentInputDTO);

        // Verify
        assertEquals(1, result.getIdStudent());
        assertEquals(1, result.getIdPerson());
        assertEquals(null, result.getIdTeacher());
        assertEquals(0, result.getNumHoursWeek());
        assertEquals("Aprobado", result.getComments());
        assertEquals("Historia", result.getBranch());
        assertEquals(Collections.emptyList(), result.getSubjects());

        verify(studentRepository, times(1)).findById(1);
        verify(personRepository, times(1)).findById(1);
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    void testDeleteStudent() {
        // Setup
        Student student = new Student();
        student.setIdStudent(1);

        // Execute
        studentService.deleteStudent(1);

        // Verify
        verify(studentRepository, times(1)).deleteById(1);
    }

    @Test
    void testDeleteSubjects() {
        // Setup
        Student student = new Student();
        student.setIdStudent(1);

        Person person = new Person();
        student.setPerson(person);

        when(studentRepository.findById(1)).thenReturn(Optional.of(student));

        Subject subject1 = new Subject();
        Subject subject2 = new Subject();
        subject1.setIdSubject(1);
        subject2.setIdSubject(2);

        List<Subject> subjects = new ArrayList<>();
        subjects.add(subject1);
        subjects.add(subject2);

        student.setSubjects(subjects);

        when(subjectRepository.findAllById(Arrays.asList(1, 2))).thenReturn(Arrays.asList(subject1, subject2));

        // Execute
        StudentOutputDTO result = studentService.deleteSubjects(1, Arrays.asList(1, 2));

        // Verify
        assertEquals(1, result.getIdStudent());
        assertEquals(Collections.emptyList(), result.getSubjects());

        verify(studentRepository, times(1)).findById(1);
        verify(subjectRepository, times(1)).findAllById(Arrays.asList(1, 2));
        verify(studentRepository, times(1)).save(student);
    }
}
