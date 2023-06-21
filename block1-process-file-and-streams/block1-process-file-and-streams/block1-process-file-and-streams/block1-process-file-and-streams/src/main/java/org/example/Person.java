package org.example;


// Creamos la clase persona
public class Person {
    // Le asignamos sus atributos
    private String name;
    private String town;
    private int age;

    // Creamos el constructor
    public Person(String name, String town, int age) {
        this.name = name;
        this.town = town;
        this.age = age;
    }

    // Creamos los Getters y Setters para obtener y modificar los valores de los atributos en caso de ser necesario
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    @Override
    public String toString() {
        return "Name: " + (getName() != null ? getName() : "Unknown")+
                " Age: " + (getAge() != 0 ? getAge() : "Unknown")+
                " Town: " + (getTown() != null && !getTown().isEmpty() ? getTown() : "Unknown");
    }
}

