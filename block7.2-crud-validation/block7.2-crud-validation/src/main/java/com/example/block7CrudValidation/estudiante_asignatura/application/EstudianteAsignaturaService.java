<<<<<<< HEAD
package com.example.block7CrudValidation.estudiante_asignatura.application;

import com.example.block7CrudValidation.estudiante_asignatura.domain.EstudianteAsignatura;
import java.util.List;

public interface EstudianteAsignaturaService {

    EstudianteAsignatura createEstudianteAsignatura(EstudianteAsignatura estudianteAsignatura);
    EstudianteAsignatura updateEstudianteAsignatura(EstudianteAsignatura estudianteAsignatura);
    EstudianteAsignatura getById(int id);
    List<EstudianteAsignatura> getAll();
    List<EstudianteAsignatura> getByStudent(int id);
    void deleteEstudianteAsignatura(int id);
}
=======
package com.example.block7CrudValidation.estudiante_asignatura.application;

import com.example.block7CrudValidation.estudiante_asignatura.domain.EstudianteAsignatura;
import java.util.List;

public interface EstudianteAsignaturaService {

    EstudianteAsignatura createEstudianteAsignatura(EstudianteAsignatura estudianteAsignatura);
    EstudianteAsignatura updateEstudianteAsignatura(EstudianteAsignatura estudianteAsignatura);
    EstudianteAsignatura getById(String id);
    List<EstudianteAsignatura> getAll();
    List<EstudianteAsignatura> getByStudent(String idStudent);
    void deleteEstudianteAsignatura(String id);
}
>>>>>>> 0215ed2c784a238139adc681a90ff64f94587231
