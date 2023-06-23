<<<<<<< HEAD
package com.example.block7CrudValidation.student.application;

import com.example.block7CrudValidation.student.domain.Student;
import java.util.List;

public interface StudentService {
    Student createStudent(Student student);
    Student updateStudent(Student student);
    void deleteStudent(int id);
    List<Student> getAllStudents();
    Student getStudentById(int id);
}
=======
package com.example.block7CrudValidation.student.application;

import com.example.block7CrudValidation.student.domain.Student;
import java.util.List;

public interface StudentService {
    Student createStudent(Student student);
    Student updateStudent(Student student);
    void deleteStudent(String id);

    List<Student> getAllStudents();

    Student getStudentById(String id);
}
>>>>>>> 0215ed2c784a238139adc681a90ff64f94587231
