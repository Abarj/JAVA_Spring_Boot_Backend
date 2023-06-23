<<<<<<< HEAD
package com.example.block7CrudValidation.profesor.domain;

import com.example.block7CrudValidation.persona.domain.Persona;
import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "profesor")
public class Profesor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id_profesor;

    @OneToOne
    @JoinColumn(name = "id_persona")
    private Persona persona;

    @Column
    private String comments;

    @Column(nullable = true)
    private String branch;

    public int getId_profesor() {
        return id_profesor;
    }

    public void setId_profesor(int id_profesor) {
        this.id_profesor = id_profesor;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
}
=======
package com.example.block7CrudValidation.profesor.domain;

import com.example.block7CrudValidation.persona.domain.Persona;
import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "profesor")
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String id_profesor;

    @OneToOne
    @JoinColumn(name = "id_persona")
    private Persona persona;

    @Column
    private String comments;

    @Column(nullable = false)
    private String branch;
}
>>>>>>> 0215ed2c784a238139adc681a90ff64f94587231
