<<<<<<< HEAD
package com.example.block7CrudValidation.student.application;

import com.example.block7CrudValidation.student.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
}
=======
package com.example.block7CrudValidation.student.application;

import com.example.block7CrudValidation.student.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
}
>>>>>>> 0215ed2c784a238139adc681a90ff64f94587231
