package com.example.block17.SpringBatchChunk;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class Application {

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job job;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	// Implicitamente vamos a recibir los args en el Runner y se procesan retornándolos con una expresión lambda
	CommandLineRunner init() {
		return args -> {
			JobParameters jobParameters = new JobParametersBuilder()
					.addString("name", "chunk")
					.addLong("id", System.currentTimeMillis())
					.addDate("date", new Date())
					.toJobParameters();

			jobLauncher.run(job, jobParameters);
		};
	}
}
