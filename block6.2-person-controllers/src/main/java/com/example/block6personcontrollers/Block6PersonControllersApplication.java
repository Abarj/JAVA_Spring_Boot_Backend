package com.example.block6personcontrollers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Block6PersonControllersApplication {

	public static void main(String[] args) {
		SpringApplication.run(Block6PersonControllersApplication.class, args);
	}

	@Bean
	public List<Ciudad> ciudades() {
		List<Ciudad> ciudades = new ArrayList<>();

		Ciudad ciudad1 = new Ciudad("Madrid", 100000);
		Ciudad ciudad2 = new Ciudad("Logroño", 200000);
		Ciudad ciudad3 = new Ciudad("Barcelona", 150000);

		ciudades.add(ciudad1);
		ciudades.add(ciudad2);
		ciudades.add(ciudad3);

		return ciudades;
	}

	@Bean("bean1")
	Persona persona1(){
		Persona persona = new Persona("David", "Zaragoza", 26);

		return persona;
	}

	@Bean("bean2")
	Persona persona2(){
		Persona persona = new Persona("Patricia", "Valencia", 31);

		return persona;
	}

	@Bean("bean3")
	Persona persona3(){
		Persona persona = new Persona("Claudia", "Logroño", 42);

		return persona;
	}

}
