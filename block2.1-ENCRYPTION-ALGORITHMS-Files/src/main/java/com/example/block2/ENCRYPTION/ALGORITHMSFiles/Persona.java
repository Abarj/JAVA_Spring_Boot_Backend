package com.example.block2.ENCRYPTION.ALGORITHMSFiles;

import java.io.Serializable;

public class Persona implements Serializable {
    private String nombre;
    private String apellido;
    private String ciudad;
    private String nacionalidad;
    private int edad;
    private static final long serialVersionUID = 4292603112202706217L;

    public Persona() {
    }

    public Persona(String nombre, String apellido, String ciudad, String nacionalidad, int edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.ciudad = ciudad;
        this.nacionalidad = nacionalidad;
        this.edad = edad;
    }

    public Persona(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + "\n" +
                "Apellido: " + apellido + "\n" +
                "Ciudad: " + ciudad + "\n" +
                "Nacionalidad: " + nacionalidad + "\n" +
                "Edad: " + edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void mostrarTodosLosCampos() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Apellido: " + apellido);
        System.out.println("Ciudad: " + ciudad);
        System.out.println("Nacionalidad: " + nacionalidad);
        System.out.println("Edad: " + edad);
    }
}


