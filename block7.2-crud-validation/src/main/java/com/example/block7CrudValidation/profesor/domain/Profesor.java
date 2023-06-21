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
