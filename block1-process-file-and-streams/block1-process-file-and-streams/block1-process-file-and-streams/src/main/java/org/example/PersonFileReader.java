package org.example;

import org.example.InvalidLineFormatException;
import org.example.Person;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PersonFileReader {
    // Creamos la función readPersonsFromFile
    // La función recibe como parámetro una ruta de un archivo
    public List<Person> readPersonsFromFile(String filePath) throws IOException, InvalidLineFormatException {
        // Creamos el array de personas
        List<Person> persons = new ArrayList<>();

        // Introducimos la ruta del archivo que queremos leer
        Path archivo = Paths.get(filePath);

        try {
            // Le pasamos el archivo que tenemos asignado en la variable file al método readAllLines
            List<String> lines = Files.readAllLines(archivo);

            // Leemos el número de líneas para poder ir leyendo el archivo
            int numeroLinea = 0;
            for (String line : lines) {
                // Pasamos de línea una vez seteada ésta
                numeroLinea++;

                try {
                    // Seteamos cada persona linea por linea
                    Person person = parsePersonFromLine(line);
                    // Introducimos la persona en el array de persons
                    persons.add(person);
                } catch (InvalidLineFormatException e) {
                    // Lanzamos excepción
                    String mensajeError = "Error parseando la línea" + numeroLinea + ": " + line + ". Causa: " + e.getMessage();
                    throw new InvalidLineFormatException(mensajeError);
                }
            }
        } catch (IOException e) {
            // Lanzamos excepción
            String mensajeError = "Error leyendo el archivo: ";
            throw new IOException(mensajeError + filePath, e);
        }

        // Devolvemos el array de personas
        return persons;
    }

    // Creamos una función para filtrar las personas obtenidas al leer el fichero y devolver esa lista de personas
    // Esta función toma dos parámetros 'persons' que es una lista de objetos, 'town' (String de ciudad) y 'age' (Entero de edad)
    public List<Person> filterPersons(List<Person> persons, String town, int minAge, int maxAge) {
        // Se llama al método stream en la lista persons
        return persons.stream()
                // Utilizamos el método filter para filtrar los elementos (personas) que cumplan una determinada condición

                // Filtramos por ciudad y comprobamos que no sea nulo (town no es obligatorio)
                .filter(person -> person.getTown() != null && person.getTown().equalsIgnoreCase(town))
                // Filtramos por edad y comprobamos que no tenga edad desconocida
                .filter(person -> minAge == -1 || maxAge == -1 || (person.getAge() >= minAge && person.getAge() <= maxAge))

                // Utilizamos el método collect para recolectar las personas filtradas
                // Utilizamos Collectors.toList() para especificar que deseamos recolectarlos en una lista
                .collect(Collectors.toList());
    }

    // Creamos una función para filtrar las personas por edad
    public List<Person> filterPersonsByAge(List<Person> persons, int minAge, int maxAge) {
        // Se llama al método stream en la lista persons
        return persons.stream()
                // Filtramos por edad y comprobamos que no tenga edad desconocida
                .filter(person -> minAge == -1 || maxAge == -1 || (person.getAge() >= minAge && person.getAge() <= maxAge))
                // Recolectamos las personas filtradas
                .collect(Collectors.toList());
    }

    // Creamos una función para filtrar las personas por ciudad
    public List<Person> filterPersonsByCity(List<Person> persons, String town) {
        // Se llama al método stream en la lista persons
        return persons.stream()
                // Filtramos por ciudad
                .filter(person -> person.getTown() != null && person.getTown().equalsIgnoreCase(town))
                // Recolectamos las personas filtradas
                .collect(Collectors.toList());
    }

    // Creamos un función para parsear los datos de cada linea del csv y convertirlos al formato deseado
    // Esta función recibe como parámetro cada una de las lineas que vamos leyendo, una por una.
    private Person parsePersonFromLine(String line) throws InvalidLineFormatException {
        // Creamos un array y especificamos que nos separe los campos cada :
        String[] campos = line.split(":");

        // Si no hay campos o hay más de 3 lanzamos excepción de error de formato
        if (campos.length == 0 || campos.length > 3) {
            // Lanzamos excepción
            String mensajeError = "Formato de línea inválido";
            throw new InvalidLineFormatException(mensajeError);
        }

        // Asignamos a las variables name, town y age los campos correspondientes a cada una de ellas
        // Name siempre existe en el archivo por lo que no comprobamos si existe
        String name = campos[0];
        // Aquí comprobamos si existen más campos (ya que no son obligatorios town y age)
        // En caso afirmativo asginamos esos campos a su variable correspondiente
        String town = campos.length > 1 ? campos[1] : "";
        int age = campos.length > 2 ? parseAge(campos[2]) : 0;

        // Devolvemos UNA nueva persona que se asignará al array de la función readPersonsFromFile (persons.add(person));
        return new Person(name, town, age);
    }

    // Creamos una función para parsear la edad
    // Recibe como parámetro un String con la edad, el cual convertirá a Entero
    private int parseAge(String ageStr) throws InvalidLineFormatException {
        try {
            // Parseamos el string de la edad para pasarlo a entero
            int age = Integer.parseInt(ageStr);

            // Comprobamos si tiene edad desconocida
            if (age == 0) {
                // Persona con edad desconocida
                return -1;
            }

            // Devolvemos la edad en Entero (en caso de que ésta sea conocida
            return age;
        } catch (NumberFormatException e) {
            // Lanzamos excepción
            String mensajeError = "Formato de edad incorrecto";
            throw new InvalidLineFormatException(mensajeError);
        }
    }
}
