package com.example.block5logging;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Block5LoggingApplication implements CommandLineRunner {
	Logger logger = LoggerFactory.getLogger(Block5LoggingApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(Block5LoggingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.warn("->Este es un mensaje de advertencia");
		logger.info("->Este es un mensaje de información");
		logger.error("-> Este es un mensaje de error");
		logger.debug("->Este es un mensaje de depuración");
		logger.trace("->Este es un mensaje de traza");
	}
}
