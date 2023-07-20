package com.example.block15kafkaprovider;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class Block15KafkaProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(Block15KafkaProviderApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(KafkaTemplate<String, String> kafkaTemplate){
		return args -> {
			kafkaTemplate.send("unProgramadorNace-topic", "Mensaje con Kafka desde Spring TEST");
		};
	}
}