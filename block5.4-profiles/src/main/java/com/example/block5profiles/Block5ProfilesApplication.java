package com.example.block5profiles;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@SpringBootApplication
@PropertySource(value = { "classpath:application.local.properties", "classpath:application.int.properties", "classpath:application.pro.properties" })
public class Block5ProfilesApplication implements CommandLineRunner {

	private final Environment environment;

	public Block5ProfilesApplication(Environment environment) {
		this.environment = environment;
	}

	public static void main(String[] args) {
		SpringApplication.run(Block5ProfilesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String perfil = environment.getProperty("spring.profiles.active");
		String dbUrl = environment.getProperty("bd.url");

		System.out.println("Perfil: " + perfil);
		System.out.println("Database URL: " + dbUrl);
	}
}
