<<<<<<< HEAD
package com.example.block7CrudValidation.estudiante_asignatura.domain;

import com.example.block7CrudValidation.student.domain.Student;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "estudiante_asignatura")
public class EstudianteAsignatura implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id_subject;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_student")
    Student student;

    @Column
    private String subject;

    @Column
    private Date initial_date;

    @Column
    private Date finish_date;
}
=======
package com.example.block7CrudValidation.estudiante_asignatura.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "estudiante_asignatura")
public class EstudianteAsignatura {

    @Id
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
