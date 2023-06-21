package com.example.block5.properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

		try {
			Resource resource = new ClassPathResource("application.yml");
			InputStream inputStream = resource.getInputStream();
			Yaml yaml = new Yaml();
			Map<String, Object> properties = yaml.load(inputStream);

			String greeting = StringUtils.isEmpty(properties.get("greeting")) ? "No se encontró la propiedad 'greeting'" : properties.get("greeting").toString();
			String myNumber = StringUtils.isEmpty(properties.get("my.number")) ? "No se encontró la propiedad 'my.number'" : properties.get("my.number").toString();
			String newProperty = System.getenv("new.property");

			System.out.println("El valor de greeting es: " + greeting);
			System.out.println("El valor de my.number es: " + myNumber);
			System.out.println("El valor de new.property es: " + newProperty);
		} catch (IOException e) {
			System.out.println("No se pudo cargar el archivo application.yml");
			e.printStackTrace();
		}
	}

}
