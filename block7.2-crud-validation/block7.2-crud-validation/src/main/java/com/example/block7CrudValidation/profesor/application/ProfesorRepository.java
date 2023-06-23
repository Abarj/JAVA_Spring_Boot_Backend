<<<<<<< HEAD
package com.example.block7CrudValidation.profesor.application;

import com.example.block7CrudValidation.profesor.domain.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, Integer> {
    List<Profesor> findByBranch(String branch);
}
=======
package com.example.block7CrudValidation.profesor.application;

import com.example.block7CrudValidation.profesor.domain.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, String> {
    List<Profesor> findByBranch(String branch);
}
>>>>>>> 0215ed2c784a238139adc681a90ff64f94587231
