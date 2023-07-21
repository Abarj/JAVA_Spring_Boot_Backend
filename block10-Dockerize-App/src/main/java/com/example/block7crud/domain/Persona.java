package com.example.block7crud.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column
    String nombre;
    @Column
    Integer edad;
    @Column
    String poblacion;

    public Long getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = Long.valueOf(id);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    @Override
    public String toString() {
        return  "{\n" +
                "id : " + id + ", " + "\n" +
                "name : " + nombre + "," + "\n" +
                "age : " + edad + "," + "\n" +
                "population : " + poblacion + "\n" +
                "}\n";
    }
}