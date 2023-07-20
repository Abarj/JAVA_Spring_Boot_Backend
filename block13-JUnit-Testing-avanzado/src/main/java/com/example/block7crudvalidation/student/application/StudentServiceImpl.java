package com.example.block7crudvalidation.student.application;

import com.example.block7crudvalidation.person.domain.Person;
import com.example.block7crudvalidation.person.infrastructure.repository.PersonRepository;
import com.example.block7crudvalidation.student.domain.Student;
import com.example.block7crudvalidation.student.infrastructure.dto.input.StudentInputDTO;
import com.example.block7crudvalidation.student.infrastructure.dto.output.StudentFullOutputDTO;
import com.example.block7crudvalidation.student.infrastructure.dto.output.StudentOutputDTO;
import com.example.block7crudvalidation.student.infrastructure.repository.StudentRepository;
import com.example.block7crudvalidation.subject.domain.Subject;
import com.example.block7crudvalidation.subject.infrastructure.repository.SubjectRepository;
import com.example.block7crudvalidation.teacher.domain.Teacher;
import com.example.block7crudvalidation.teacher.infrastructure.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    SubjectRepository subjectRepository;

    @Override
    public StudentOutputDTO addStudent(StudentInputDTO studentInputDTO) {
        Person person = personRepository.findById(studentInputDTO.getIdPerson())
                .orElseThrow(() -> new RuntimeException("No person found with id: " + studentInputDTO.getIdPerson()));
        Teacher teacher = null;
        if (studentInputDTO.getIdTeacher() != null) {
            teacher = teacherRepository.findById(studentInputDTO.getIdTeacher())
                    .orElseThrow(() -> new RuntimeException("No teacher found with id: " + studentInputDTO.getIdPerson()));
        }
        List<Subject> subjects = getSubjectsIds(studentInputDTO.getIdSubjects());
        Student student = new Student(studentInputDTO);
        student.setPerson(person);
        student.setTeacher(teacher);
        student.setSubjects(subjects);
        subjects.forEach(subject -> subject.addStudent(student));
        studentRepository.save(student);

        return new StudentOutputDTO(student);
    }

    @Override
    public List<StudentOutputDTO> getAllStudents(String outputType) {
        List<Student> students = studentRepository.findAll();
        List<StudentOutputDTO> studentOutputDTOs = new ArrayList<>();

        for (Student student : students) {
            if ("full".equals(outputType)) {
                studentOutputDTOs.add(new StudentFullOutputDTO(student));
            } else {
                studentOutputDTOs.add(new StudentOutputDTO(student));
            }
        }

        return studentOutputDTOs;
    }

    @Override
    public StudentOutputDTO addSubjects(Integer idStudent, Collection<Integer> subjectsInsert) {
        Student student = studentRepository.findById(idStudent)
                .orElseThrow(() -> new RuntimeException("No student found with id: " + idStudent));
        List<Integer> idsSubjects = student.getSubjects().stream().map(Subject::getIdSubject)
                .collect(Collectors.toList());
        idsSubjects.addAll(subjectsInsert);
        List<Subject> subjects = getSubjectsIds(idsSubjects.stream().distinct().collect(Collectors.toList()));
        student.setSubjects(subjects);
        subjects.forEach(subject -> subject.addStudent(student));
        studentRepository.save(student);

        return new StudentOutputDTO(student);
    }

    private List<Subject> getSubjectsIds(List<Integer> ids) {
        List<Subject> subjects = new ArrayList<>();
        if (ids != null) {
            subjects = subjectRepository.findAllById(ids);
            if (ids.size() != subjects.size()) {
                throw new RuntimeException("No se han encontrado asignaturas con el ID: " + ids);
            }
        }

        return subjects;
    }

    @Override
    public Student getStudentId(Integer id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se ha encontrado al estudiante con el ID: " + id));
    }

    @Override
    public StudentOutputDTO updateStudent(Integer id, StudentInputDTO studentInputDTO) {
        Student foundStudent = getStudentId(id);
        Person person = personRepository.findById(studentInputDTO.getIdPerson())
                .orElseThrow(() -> new RuntimeException("No se ha encontrado a la persona con el ID: " + id));
        foundStudent.update(studentInputDTO);
        foundStudent.setPerson(person);
        studentRepository.save(foundStudent);
        return new StudentOutputDTO(foundStudent);
    }

    @Override
    public void deleteStudent(Integer id) {
        studentRepository.deleteById(id);
    }

    @Override
    public StudentOutputDTO deleteSubjects(Integer idStudent, List<Integer> subjectsDelete) {
        Student student = studentRepository.findById(idStudent)
                .orElseThrow(() -> new RuntimeException("No se ha encontrado al estudiante con el ID: " + idStudent));
        List<Subject> subjects = getSubjectsIds(subjectsDelete);
        student.getSubjects().removeAll(subjects);
        subjects.forEach(subject -> subject.deleteStudent(student));
        studentRepository.save(student);

        return new StudentOutputDTO(student);
    }

    @Override
    public StudentOutputDTO getStudentById(Integer idStudent) {
        Student student = studentRepository.findById(idStudent)
                .orElseThrow(() -> new RuntimeException("No se ha encontrado al estudiante con el ID: " + idStudent));
        return new StudentOutputDTO(student);
    }

    public void deleteAllStudents() {
        studentRepository.deleteAll();
    }
}
