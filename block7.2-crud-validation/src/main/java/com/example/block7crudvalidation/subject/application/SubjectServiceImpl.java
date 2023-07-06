package com.example.block7crudvalidation.subject.application;

import com.example.block7crudvalidation.student.domain.Student;
import com.example.block7crudvalidation.student.infrastructure.repository.StudentRepository;
import com.example.block7crudvalidation.subject.domain.Subject;
import com.example.block7crudvalidation.subject.infrastructure.dto.input.SubjectInputDTO;
import com.example.block7crudvalidation.subject.infrastructure.dto.output.SubjectOutputDTO;
import com.example.block7crudvalidation.subject.infrastructure.repository.SubjectRepository;
import com.example.block7crudvalidation.teacher.domain.Teacher;
import com.example.block7crudvalidation.teacher.infrastructure.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    StudentRepository studentRepository;

    @Override
    public SubjectOutputDTO addSubject(SubjectInputDTO subjectInputDTO) {
        Subject subject = new Subject(subjectInputDTO);
        if (subjectInputDTO.getIdTeacher() != null) {
            subject.setTeacher(getTeacher(subjectInputDTO.getIdTeacher()));
        }
        List<Student> students = getStudentsIds(subjectInputDTO.getIdsStudents());
        subject.setStudents(students);
        subjectRepository.save(subject);

        return new SubjectOutputDTO(subject);
    }

    @Override
    public List<SubjectOutputDTO> getAllSubjects() {
        List<Subject> subjects = subjectRepository.findAll();
        List<SubjectOutputDTO> subjectOutputDTOs = new ArrayList<>();

        for (Subject subject : subjects) {
            subjectOutputDTOs.add(new SubjectOutputDTO(subject));
        }

        return subjectOutputDTOs;
    }

    private Teacher getTeacher(Integer id) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No teacher found with id: " + id));
    }

    private List<Student> getStudentsIds(List<Integer> ids) {
        List<Student> students = new ArrayList<>();
        if (ids != null) {
            students = studentRepository.findAllById(ids);
            if (ids.size() != students.size()) {
                throw new RuntimeException("No students found with ids: " + ids);
            }
        }

        return students;
    }

    @Override
    public SubjectOutputDTO getSubjectById(Integer id) {
        return new SubjectOutputDTO(subjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se ha encontrado ninguna asignatura con ID: " + id)));
    }

    @Override
    public List<SubjectOutputDTO> getSubjectStudentById(Integer idStudent) {
        Student student = studentRepository.findById(idStudent)
                .orElseThrow(() -> new RuntimeException("No se han encontrado estudiantes con el siguiente ID: " + idStudent));

        return student.getSubjects().stream().map(SubjectOutputDTO::new).collect(Collectors.toList());
    }

    @Override
    public SubjectOutputDTO updateSubject(Integer id, SubjectInputDTO subjectInputDTO) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se ha encontrado ninguna asignatura con ID: " + id));
        subject.update(subjectInputDTO);
        if (subjectInputDTO.getIdTeacher() != null) {
            subject.setTeacher(getTeacher(subjectInputDTO.getIdTeacher()));
        }
        List<Integer> students = new ArrayList<>(
                subject.getStudents().stream().map(Student::getIdStudent).toList());
        students.addAll(subjectInputDTO.getIdsStudents());
        subject.setStudents(getStudentsIds(students.stream().distinct().toList()));
        subjectRepository.save(subject);

        return new SubjectOutputDTO(subject);
    }

    @Override
    public void deleteSubject(Integer id) {
        subjectRepository.deleteById(id);
    }
}