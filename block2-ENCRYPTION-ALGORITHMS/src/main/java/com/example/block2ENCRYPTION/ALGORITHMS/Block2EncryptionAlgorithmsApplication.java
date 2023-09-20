package com.example.block2ENCRYPTION.ALGORITHMS;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;


@SpringBootApplication
public class Block2EncryptionAlgorithmsApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(Block2EncryptionAlgorithmsApplication.class, args);
	}

	public static SecretKey generarClave() throws Exception {
		KeyGenerator generador = KeyGenerator.getInstance("DES");
		SecretKey clave = generador.generateKey(); // Genera una clave secreta para el cifrado DES
		return clave;
	}

	public static void cifrarArchivo(String archivoEntrada, String archivoSalida, SecretKey clave) throws Exception {
		Cipher cifrador = Cipher.getInstance("DES/ECB/PKCS5Padding");
		cifrador.init(Cipher.ENCRYPT_MODE, clave); // Inicializa el cifrador en modo de cifrado

		File inputFile = new File(archivoEntrada); // Usa la clase File para representar el archivo
		FileInputStream fis = new FileInputStream(inputFile);

		byte[] inputBytes = new byte[(int) inputFile.length()]; // Obtiene la longitud del archivo
		fis.read(inputBytes); // Lee el archivo de entrada

		byte[] outputBytes = cifrador.doFinal(inputBytes); // Realiza el cifrado

		FileOutputStream fos = new FileOutputStream(archivoSalida);
		fos.write(outputBytes); // Escribe el archivo cifrado

		fis.close();
		fos.close();

		System.out.println("Archivo cifrado exitosamente.");
	}

	public static void descifrarArchivo(String archivoEntrada, String archivoSalida, SecretKey clave) throws Exception {
		Cipher descifrador = Cipher.getInstance("DES/ECB/PKCS5Padding");
		descifrador.init(Cipher.DECRYPT_MODE, clave); // Inicializa el descifrador en modo de descifrado

		File archivoCifrado = new File(archivoEntrada);
		FileInputStream fis = new FileInputStream(archivoCifrado);

		byte[] entradaCifrada = new byte[(int) archivoCifrado.length()];
		fis.read(entradaCifrada);

		byte[] salidaDescifrada = descifrador.doFinal(entradaCifrada); // Realiza el descifrado

		File archivoDescifrado = new File(archivoSalida);
		FileOutputStream fos = new FileOutputStream(archivoDescifrado);
		fos.write(salidaDescifrada); // Escribe el archivo descifrado

		fis.close();
		fos.close();

		System.out.println("Archivo descifrado exitosamente.");
	}

	@Override
	public void run(String... args) throws Exception {
		try {
			Scanner scanner = new Scanner(System.in);
			SecretKey clave = generarClave(); // Genera una clave secreta para el cifrado DES

			boolean salir = false;
			while (!salir) {
				System.out.println("\nMenú:");
				System.out.println("1. Cifrar");
				System.out.println("2. Descifrar");
				System.out.println("3. Salir");
				System.out.print("Seleccione una opción: ");

				int opcion = scanner.nextInt();
				scanner.nextLine();  // Consumir el salto de línea

				switch (opcion) {
					case 1:
						System.out.print("Ingrese el nombre del archivo de entrada (texto plano): ");
						String archivoEntrada = scanner.nextLine();
						System.out.print("Ingrese el nombre del archivo de salida (encriptado): ");
						String archivoSalida = scanner.nextLine();
						cifrarArchivo(archivoEntrada, archivoSalida, clave); // Llama a la función de cifrado
						break;
					case 2:
						System.out.print("Ingrese el nombre del archivo encriptado: ");
						archivoEntrada = scanner.nextLine();
						System.out.print("Ingrese el nombre del archivo de salida (texto plano): ");
						archivoSalida = scanner.nextLine();
						descifrarArchivo(archivoEntrada, archivoSalida, clave); // Llama a la función de descifrado
						break;
					case 3:
						salir = true;
						break;
					default:
						System.out.println("Opción no válida. Intente nuevamente.");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
