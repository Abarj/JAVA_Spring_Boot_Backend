package com.example.block17.SpringBatchChunk.config;

import com.example.block17.SpringBatchChunk.entity.Person;
import com.example.block17.SpringBatchChunk.steps.PersonItemProcessor;
import com.example.block17.SpringBatchChunk.steps.PersonItemReader;
import com.example.block17.SpringBatchChunk.steps.PersonItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public PersonItemReader itemReader() {
        return new PersonItemReader();
    }

    @Bean
    public PersonItemWriter itemWriter() {
        return new PersonItemWriter();
    }

    @Bean
    public PersonItemProcessor itemProcessor() {
        return new PersonItemProcessor();
    }



    @Bean
    // Objeto de ExecutorFramework para gestionar hilos -> Mecanismo que utiliza SpringBatch para coordinar los hilos
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(1); // Cuandos hilos se van a ejecutar al iniciar la aplicación
        taskExecutor.setMaxPoolSize(5); // Hilos adicionales que se pueden desplegar en caso de ser necesarios
        taskExecutor.setQueueCapacity(5); // Máximo de tareas en cola

        return taskExecutor;
    }

    @Bean
    public Step readFile() {
        return stepBuilderFactory.get("readFile")
                .<Person, Person>chunk(10) // Lotes de 10 registros
                .reader(itemReader())
                .processor(itemProcessor())
                .writer(itemWriter())
                .taskExecutor(taskExecutor())
                .build();
    }

    @Bean
    public Job job() {
        return jobBuilderFactory.get("readFileWithChunk")
                .start(readFile())
                .build();
    }
}
