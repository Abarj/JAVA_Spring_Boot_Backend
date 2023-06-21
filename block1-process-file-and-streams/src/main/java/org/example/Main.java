package org.example;

import org.example.PersonFileReader;
import org.example.Person;
import org.example.InvalidLineFormatException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // Ruta relativa del archivo CSV
        String filePath = "src/main/resources/people.csv";
        // Crear instancia de PersonFileReader
        PersonFileReader fr = new PersonFileReader();

        try {
            // Leer las personas desde el archivo CSV
            List<Person> persons = fr.readPersonsFromFile(filePath);

            // Crear instancia de Scanner para preguntar al usuario
            Scanner scanner = new Scanner(System.in);
            // Creamos la varible opción
            int opcion = 0;

            while (opcion != 8) {
                // Llamamos a la función mostrarMenu() para mostrar el menu al usuario
                mostrarMenu();

                // Le pedimos al usuario que introduzca una opción
                System.out.print("Introduzca una opción: ");
                opcion = scanner.nextInt();


                switch (opcion) {
                    case 1:
                        mostrarTodasPersonas(persons);
                        break;
                    case 2:
                        filtrarPorEdad(persons);
                        break;
                    case 3:
                        filtrarPorCiudad(persons);
                        break;
                    case 4:
                        obtenerMenoresDe25(persons);
                        break;
                    case 5:
                        filtrarNombresSinA(persons);
                        break;
                    case 6:
                        obtenerPrimeroMadrid(persons);
                        break;
                    case 7:
                        obtenerPrimeroBcn(persons);
                        break;
                    case 8:
                        salir();
                        break;
                    // Default cuando ninguno de los casos coincida con las opciones
                    default:
                        opcionDefault();
                        break;
                }
            }
        } catch (IOException | InvalidLineFormatException e) {
            // En caso de error lanzamos excepción en la consola
            String mensajeError = "Error: ";
            System.out.println(mensajeError + e.getMessage());
        }
    }

    // Creamos el menú que se mostrará por consola al usuario mostrándole todas las opciones
    private static void mostrarMenu() {
        System.out.println("¿Qué desea hacer?");
        System.out.println("1. Obtener todas las personas");
        System.out.println("2. Filtrar por edad");
        System.out.println("3. Filtrar por ciudad");
        System.out.println("----------Funciones especiales----------");
        System.out.println("4. <25años");
        System.out.println("5. !A%");
        System.out.println("6. <25&Madrid");
        System.out.println("7. <25&Bcn");
        System.out.println("8. Salir");
    }

    private static void mostrarTodasPersonas(List<Person> persons) {
        // Recorremos e imprimimos TODAS las personas
        for (Person person : persons) {
            System.out.println(person);
        }
        System.out.println("----------");
    }

    private static void filtrarPorEdad(List<Person> persons) {
        // ---------Filtrar por edad---------
        // Instanciamos FileReader
        PersonFileReader fr = new PersonFileReader();
        // Instanciamos Scanner
        Scanner scannerAge = new Scanner(System.in);
        // Preguntamos al usuario cuales son sus filtros de edad
        System.out.print("Ingrese el valor mínimo de edad: ");
        // Se asigna cada valor a su variable correcpondiente
        int minAge = scannerAge.nextInt();
        System.out.print("Ingrese el valor máximo de edad: ");
        int maxAge = scannerAge.nextInt();
        System.out.println("");

        // Ejecutamos la función pasándole los parámetros requeridos
        List<Person> filteredByAge = fr.filterPersonsByAge(persons, minAge, maxAge);

        System.out.println("Personas de edad entre: " + minAge + " y " + maxAge);
        // Imprimir las personas filtradas
        for (Person person : filteredByAge) {
            System.out.println(person);
            System.out.println("----------");
        }
        System.out.println("----------");
    }

    private static void filtrarPorCiudad(List<Person> persons) {
        // ---------Filtrar por ciudad---------
        // Instanciamos Scanner
        Scanner scannerTown = new Scanner(System.in);
        // Instanciamos FileReader
        PersonFileReader fr = new PersonFileReader();
        // Preguntamos al usuario por consola cual es la ciudad que desea filtrar
        System.out.print("Ingrese la ciudad por la cual desea filtrar: ");
        // Asignamos el string introducido por el usuario a la variable town
        String town = scannerTown.nextLine();
        System.out.println("");

        // Ejecutamos la función pasándole los parámetros requeridos
        List<Person> personasFiltradas = fr.filterPersonsByCity(persons, town);

        // Imprimir las personas filtradas
        System.out.println("Personas en " + town + ":");

        // Mostramos las personas filtradas
        for (Person person : personasFiltradas) {
            System.out.println(person);
            System.out.println("----------");
        }
        System.out.println("----------");
    }

    public static void obtenerMenoresDe25(List<Person> persons) {
        // ---------Filtrar personas menores de 25 años---------
        // Instanciamos FileReader
        PersonFileReader fr = new PersonFileReader();
        // Ejecutamos la función filterPersonsByAge() pasándole los parámetros requeridos (que sea <25 años)
        List<Person> filteredByAgeUnder25 = fr.filterPersonsByAge(persons, 1, 24);

        System.out.println("Personas menores de 25 años:");
        // Imprimir las personas filtradas
        for (Person person : filteredByAgeUnder25) {
            System.out.println(person);
            System.out.println("----------");
        }
        System.out.println("----------");
    }

    public static void filtrarNombresSinA(List<Person> persons) {
        // ---------Eliminar personas cuyo nombre empiece con la letra A---------
        List<Person> filteredByNameWithoutA = persons.stream()
                // Añadimos los filtros para que nos elimine las personas que empiezan por A y Á
                .filter(person -> person.getName() == null || !person.getName().startsWith("A"))
                .filter(person -> person.getName() == null || !person.getName().startsWith("Á"))
                // Recolectamos las personas filtradas
                .collect(Collectors.toList());

        System.out.println("Personas cuyo nombre no empieza con A:");
        // Imprimir las personas filtradas
        for (Person person : filteredByNameWithoutA) {
            System.out.println(person);
            System.out.println("----------");
        }
        System.out.println("----------");
    }

    public static void obtenerPrimeroMadrid(List<Person> persons) {
        // ---------Filtrar personas menores de 25 años---------
        // Instanciamos FileReader
        PersonFileReader fr = new PersonFileReader();
        List<Person> filteredByAgeUnder25Madrid = fr.filterPersonsByAge(persons, -1, 24);

        // Obtener el primer elemento cuya ciudad sea Madrid (si existe)
        Optional<Person> firstPersonFromMadrid = filteredByAgeUnder25Madrid.stream()
                .filter(person -> person.getTown() != null && person.getTown().equalsIgnoreCase("Madrid"))
                .findFirst();

        // Imprimir el primer elemento si existe
        firstPersonFromMadrid.ifPresent(person -> {
            System.out.println("Primera persona de Madrid menor de 25 años:");
            System.out.println(person);
            System.out.println("----------");
        });
        System.out.println("----------");
    }

    public static void obtenerPrimeroBcn(List<Person> persons) {
        // ---------Filtrar personas menores de 25 años---------
        // Instanciamos FileReader
        PersonFileReader fr = new PersonFileReader();
        List<Person> filteredByAgeUnder25Bcn = fr.filterPersonsByAge(persons, -1, 24);

        // Obtener el primer elemento cuya ciudad sea Barcelona (si existe)
        Optional<Person> firstPersonFromBarcelona = filteredByAgeUnder25Bcn.stream()
                .filter(person -> person.getTown() != null && person.getTown().equalsIgnoreCase("Barcelona"))
                .filter(person -> person.getAge() < 25)
                .findFirst();

        if (firstPersonFromBarcelona.isEmpty()) {
            System.out.println("----------");
            System.err.println("No hay personas menores de 25 años en Barcelona.");
        }
        else {
            // Imprimir el primer elemento si existe
            firstPersonFromBarcelona.ifPresent(person -> {
                System.out.println("Primera persona de Barcelona menor de 25 años:");
                System.out.println(person);
                System.out.println("----------");
            });
        }
        System.out.println("----------");
    }

    private static void salir() {
        // ---------Salir del programa---------
        System.out.println("Saliendo del programa...");
    }

    private static void opcionDefault() {
        System.err.println("----------");
        System.err.println("Opción inválida");
        System.err.println("----------");
    }
}
