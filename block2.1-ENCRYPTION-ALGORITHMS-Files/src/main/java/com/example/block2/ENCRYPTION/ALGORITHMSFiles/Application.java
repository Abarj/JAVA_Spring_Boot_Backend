package com.example.block2.ENCRYPTION.ALGORITHMSFiles;

// Importación de clases necesarias
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.util.Scanner;

// Anotación para marcar esta clase como una aplicación Spring Boot
@SpringBootApplication
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		// Inicia la aplicación Spring Boot
		SpringApplication.run(Application.class, args);
	}

	// Método que se ejecuta al iniciar la aplicación
	@Override
	public void run(String... args) throws Exception {
		// Crea un objeto Scanner para leer la entrada del usuario
		Scanner scanner = new Scanner(System.in);

		// Muestra un menú en la consola
		System.out.println("Menú:");
		System.out.println("1. Escribir en el archivo");
		System.out.println("2. Leer el archivo");
		System.out.println("3. Guardar datos en archivo binario");
		System.out.println("4. Leer fichero binario");
		System.out.print("Seleccione una opción (1/2/3/4): ");

		// Lee la opción seleccionada por el usuario
		int opcion = Integer.parseInt(scanner.nextLine());

		// Realiza una acción en función de la opción seleccionada
		switch (opcion) {
			case 1:
				escribirEnArchivo(scanner); // Llama al método para escribir en un archivo de texto
				break;
			case 2:
				leerArchivo(scanner); // Llama al método para leer un archivo de texto
				break;
			case 3:
				guardarEnArchivoBinario(scanner); // Llama al método para guardar datos en un archivo binario
				break;
			case 4:
				leerArchivoBinario(scanner); // Llama al método para leer un archivo binario
				break;
			default:
				System.out.println("Opción no válida.");
		}
	}

	// Método para escribir datos en un archivo de texto
	public static void escribirEnArchivo(Scanner scanner) {
		// Solicita al usuario la ruta y el nombre del archivo de texto
		System.out.print("Ingrese la ruta y nombre del archivo de texto: ");
		String rutaArchivo = scanner.nextLine();

		// Crea un objeto File para representar el archivo
		File archivo = new File(rutaArchivo);
		boolean archivoExiste = archivo.exists(); // Verifica si el archivo ya existe

		try {
			// Crea un FileWriter para escribir en el archivo (true permite añadir datos si el archivo existe)
			FileWriter fileWriter = new FileWriter(rutaArchivo, true);

			// Muestra mensajes dependiendo de si el archivo existe o se creó
			if (!archivoExiste) {
				System.out.println("El archivo se ha creado correctamente.");
			} else {
				System.out.println("Aviso: Se están añadiendo datos a un archivo existente.");
			}

			// Ingresa información de hasta tres personas en el archivo
			for (int i = 0; i < 3; i++) {
				Persona persona = new Persona();

				System.out.println("\nIngrese los datos de la Persona " + (i + 1) + ":");
				System.out.print("Nombre: ");
				persona.setNombre(scanner.nextLine());
				System.out.print("Apellido: ");
				persona.setApellido(scanner.nextLine());
				System.out.print("Ciudad: ");
				persona.setCiudad(scanner.nextLine());
				System.out.print("Nacionalidad: ");
				persona.setNacionalidad(scanner.nextLine());
				System.out.print("Edad: ");
				persona.setEdad(Integer.parseInt(scanner.nextLine()));

				// Escribe los datos de la persona en el archivo
				fileWriter.write(persona.toString() + "\n");
			}

			// Cierra el FileWriter después de escribir
			fileWriter.close();
			System.out.println("Información añadida al archivo correctamente");

		} catch (IOException e) {
			System.out.println("Error al operar con el archivo: " + e.getMessage());
		}
	}

	// Método para leer un archivo de texto
	public static void leerArchivo(Scanner scanner) {
		// Solicita al usuario la ruta y el nombre del archivo de texto
		System.out.print("Ingrese la ruta y nombre del archivo de texto: ");
		String rutaArchivo = scanner.nextLine();

		// Crea un objeto File para representar el archivo
		File archivo = new File(rutaArchivo);

		// Verifica si el archivo existe
		if (!archivo.exists()) {
			System.out.println("El archivo no existe en la ubicación especificada.");
			return;
		}

		try {
			// Crea un Scanner para leer el archivo
			Scanner fileScanner = new Scanner(archivo);

			// Muestra un menú de opciones para la lectura
			System.out.println("\nMenú de lectura:");
			System.out.println("1. Leer todo el archivo");
			System.out.println("2. Buscar una persona por nombre");
			System.out.print("Seleccione una opción (1/2): ");
			int opcion = Integer.parseInt(scanner.nextLine());

			switch (opcion) {
				case 1:
					// Lee y muestra todo el contenido del archivo
					System.out.println("\nContenido completo del archivo:");
					while (fileScanner.hasNextLine()) {
						String linea = fileScanner.nextLine();
						System.out.println(linea);
					}
					break;

				case 2:
					// Busca una persona por nombre en el archivo
					System.out.print("Ingrese el nombre de la persona que desea buscar: ");
					String nombreBuscado = scanner.nextLine();

					boolean encontrado = false;

					while (fileScanner.hasNextLine()) {
						String linea = fileScanner.nextLine();
						if (linea.contains("Nombre: " + nombreBuscado)) {
							System.out.println("\nDatos de la persona con nombre \"" + nombreBuscado + "\":");
							System.out.println(linea);

							for (int i = 0; i < 4 && fileScanner.hasNextLine(); i++) {
								System.out.println(fileScanner.nextLine());
							}
							encontrado = true;
						}
					}

					if (!encontrado) {
						System.out.println("No se encontraron personas con el nombre \"" + nombreBuscado + "\".");
					}
					break;

				default:
					System.out.println("Opción no válida.");
			}

			// Cierra el Scanner después de la lectura
			fileScanner.close();
		} catch (IOException e) {
			System.out.println("Error al leer el archivo: " + e.getMessage());
		}
	}

	// Método para guardar datos en un archivo binario
	public static void guardarEnArchivoBinario(Scanner scanner) {
		// Solicita al usuario la ruta y el nombre del archivo binario
		System.out.print("Ingrese la ruta y nombre del archivo binario: ");
		String rutaArchivoBinario = scanner.nextLine();

		// Crea un objeto File para representar el archivo binario
		File archivoBinario = new File(rutaArchivoBinario);

		// Verifica si el archivo binario existe y muestra una advertencia si es así
		if (archivoBinario.exists()) {
			System.out.println("Aviso: Se van a sobrescribir los datos existentes en el archivo binario.");
		}

		try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(archivoBinario))) {
			// Solicita al usuario el número de personas a guardar (máximo 3)
			System.out.print("Ingrese el número de personas a guardar (máximo 3): ");
			int numeroPersonas = Integer.parseInt(scanner.nextLine());

			if (numeroPersonas > 3) {
				System.out.println("El número de personas debe ser menor o igual a 3.");
				return;
			}

			// Ingresa información de personas y la guarda en el archivo binario
			for (int i = 0; i < numeroPersonas; i++) {
				Persona persona = new Persona();

				System.out.println("\nIngrese los datos de la Persona " + (i + 1) + ":");
				System.out.print("Nombre: ");
				persona.setNombre(scanner.nextLine());
				System.out.print("Apellido: ");
				persona.setApellido(scanner.nextLine());
				System.out.print("Ciudad: ");
				persona.setCiudad(scanner.nextLine());
				System.out.print("Nacionalidad: ");
				persona.setNacionalidad(scanner.nextLine());
				System.out.print("Edad: ");
				persona.setEdad(Integer.parseInt(scanner.nextLine()));

				// Escribe la persona en el archivo binario
				objectOutputStream.writeObject(persona);
			}

			System.out.println("Datos guardados en el archivo binario.");
		} catch (IOException e) {
			System.out.println("Error al operar con el archivo binario: " + e.getMessage());
		}
	}

	// Método para leer un archivo binario
	public static void leerArchivoBinario(Scanner scanner) {
		// Solicita al usuario la ruta y el nombre del archivo binario a leer
		System.out.print("Ingrese la ruta y nombre del archivo binario a leer: ");
		String rutaArchivoBinario = scanner.nextLine();

		// Crea un objeto File para representar el archivo binario
		File archivoBinario = new File(rutaArchivoBinario);

		// Verifica si el archivo binario existe
		if (!archivoBinario.exists()) {
			System.out.println("El archivo binario no existe en la ubicación especificada.");
			return;
		}

		try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(archivoBinario))) {
			// Muestra información almacenada en el archivo binario
			System.out.println("\nDatos del archivo binario:");

			while (true) {
				try {
					// Lee y muestra datos de una persona desde el archivo binario
					Persona persona = (Persona) objectInputStream.readObject();
					System.out.println("\nDatos de una persona:");
					System.out.println(persona);
				} catch (EOFException e) {
					// Maneja la excepción de fin de archivo (EOF)
					break;
				}
			}
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Error al leer el archivo binario: " + e.getMessage());
		}
	}
}