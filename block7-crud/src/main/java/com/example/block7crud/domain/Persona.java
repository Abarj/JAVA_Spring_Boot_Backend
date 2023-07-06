package com.example.block7crud;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Persona implements Serializable {
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