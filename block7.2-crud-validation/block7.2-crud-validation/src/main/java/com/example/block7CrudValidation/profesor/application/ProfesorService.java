<<<<<<< HEAD
package com.example.block7CrudValidation.profesor.application;

import com.example.block7CrudValidation.profesor.domain.Profesor;
import java.util.List;

public interface ProfesorService {
    Profesor createProfesor(Profesor profesor);
    Profesor updateProfesor(Profesor profesor);
    Profesor getById(int id);
    List<Profesor> getAll();
    List<Profesor> getByBranch(String branch);
    void deleteProfesor(int id);
}
=======
package com.example.block7CrudValidation.profesor.application;

import com.example.block7CrudValidation.profesor.domain.Profesor;
import java.util.List;

public interface ProfesorService {
    Profesor createProfesor(Profesor profesor);
    Profesor updateProfesor(Profesor profesor);
    Profesor getById(String id);
    List<Profesor> getAll();
    List<Profesor> getByBranch(String branch);
    void deleteProfesor(String id);
}
>>>>>>> 0215ed2c784a238139adc681a90ff64f94587231
