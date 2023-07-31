package com.example.block17.SpringBatchApplication.config;

import com.example.block17.SpringBatchApplication.steps.ItemDecompressStep;
import com.example.block17.SpringBatchApplication.steps.ItemProcessorStep;
import com.example.block17.SpringBatchApplication.steps.ItemReaderStep;
import com.example.block17.SpringBatchApplication.steps.ItemWriterStep;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    // ----------------------------------------------
    // Definimos los @Bean de los Tasklet
    @Bean
    @JobScope // @JobScope -> Este @Bean/paso solo tendrá el alcance del Job, es decir, cuando el Job se comience a ejecutar se creará el @Bean y una vez el Job termine se eliminará ese objeto. Unicamente se ejecutará cuando se ejecute el Job
    public ItemDecompressStep itemDecompressStep() {
        return new ItemDecompressStep();
    }

    @Bean
    @JobScope
    public ItemReaderStep itemReaderStep() {
        return new ItemReaderStep();
    }

    @Bean
    @JobScope
    public ItemProcessorStep itemProcessorStep() {
        return new ItemProcessorStep();
    }

    @Bean
    @JobScope
    public ItemWriterStep itemWriterStep() {
        return new ItemWriterStep();
    }

    // ----------------------------------------------
    // Definimos los Step
    @Bean
    public Step decompressFileStep() {
        return stepBuilderFactory.get("itemDecompressStep")
                .tasklet(itemDecompressStep())
                .build();
    }

    @Bean
    public Step readFileStep() {
        return stepBuilderFactory.get("itemReaderStep")
                .tasklet(itemReaderStep())
                .build();
    }

    @Bean
    public Step processDataStep() {
        return stepBuilderFactory.get("itemProcessorStep")
                .tasklet(itemProcessorStep())
                .build();
    }

    @Bean
    public Step writerDataStep() {
        return stepBuilderFactory.get("itemWriterStep")
                .tasklet(itemWriterStep())
                .build();
    }

    // ----------------------------------------------
    // Crear el Job con el flujo condicional que vamos a manejar
    @Bean
    public Job readCSVJob() {
        return jobBuilderFactory.get("readCSVJob")
                // Indicar el primer paso mediante .start()
                .start(decompressFileStep())
                // Indicar los siguientes pasos mediante .next()
                .next(readFileStep())
                .next(processDataStep())
                .next(writerDataStep())
                .build();
    }
}
