<<<<<<< HEAD
package com.example.block7CrudValidation.estudiante_asignatura.infrastructure;

import java.util.Date;

public class DtoEstudianteAsignatura {
    private int idSubject;
    private String studentId;
    private String subject;
    private Date initialDate;
    private Date finishDate;
}
=======
package com.example.block7CrudValidation.estudiante_asignatura.infrastructure;

import java.io.Serializable;
import java.util.Date;

public class DtoEstudianteAsignatura implements Serializable {

    private String idAsignatura;
    private String idStudent;
    private String asignatura;
    private String comments;
    private Date initialDate;
    private Date finishDate;

    public String getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(String idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public String getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(String idStudent) {
        this.idStudent = idStudent;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(Date initialDate) {
        this.initialDate = initialDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }
}
>>>>>>> 0215ed2c784a238139adc681a90ff64f94587231
