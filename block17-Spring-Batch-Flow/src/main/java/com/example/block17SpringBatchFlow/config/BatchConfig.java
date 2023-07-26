package com.example.block17SpringBatchFlow.config;

import com.example.block17SpringBatchFlow.batch.CancelTransactionTasklet;
import com.example.block17SpringBatchFlow.batch.ProcessPaymentTasklet;
import com.example.block17SpringBatchFlow.batch.SendNotificationTasklet;
import com.example.block17SpringBatchFlow.batch.ValidateAccountTasklet;
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

    // Para crear los Step y Jobs necesitamos StepBuilderFactory y JobBuilderFactory
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    // ----------------------------------------------
    // Definimos los @Bean de los Tasklet
    @Bean
    public ValidateAccountTasklet validateAccountTasklet() {
        return new ValidateAccountTasklet();
    }

    @Bean
    public ProcessPaymentTasklet processPaymentTasklet() {
        return new ProcessPaymentTasklet();
    }

    @Bean
    public CancelTransactionTasklet cancelTransactionTasklet() {
        return new CancelTransactionTasklet();
    }

    @Bean
    public SendNotificationTasklet sendNotificationTasklet() {
        return new SendNotificationTasklet();
    }

    // ----------------------------------------------
    // Definimos los Step
    @Bean
    @JobScope // @JobScope -> Este @Bean/paso solo tendrá el alcance del Job, es decir, cuando el Job se comience a ejecutar se creará el @Bean y una vez el Job termine se eliminará ese objeto. Unicamente se ejecutará cuando se ejecute el Job
    public Step validateAccountStep() {
        return stepBuilderFactory.get("validateAccountStep")
                .tasklet(validateAccountTasklet())
                .build();
    }

    @Bean
    public Step processPaymentStep() {
        return stepBuilderFactory.get("processPaymentStep")
                .tasklet(processPaymentTasklet())
                .build();
    }

    @Bean
    public Step cancelTransactionStep() {
        return stepBuilderFactory.get("cancelTransactionStep")
                .tasklet(cancelTransactionTasklet())
                .build();
    }

    @Bean
    public Step sendNotificationStep() {
        return stepBuilderFactory.get("sendNotificationStep")
                .tasklet(sendNotificationTasklet())
                .build();
    }

    // ----------------------------------------------
    // Crear el Job con el flujo condicional que vamos a manejar
    @Bean
    public Job transactionPaymentJob() {
        return jobBuilderFactory.get("transactionPaymentJob")
                // .start() -> Le indica a SpringBatch que se inicia el flujo
                .start(validateAccountStep())
                    // .on() -> Se encarga de validar el ExitStatus anterior (VALID/INVALID)
                    .on("VALID").to(processPaymentStep())
                    .next(sendNotificationStep())

                // .from() -> No ejecuta otra vez el paso si no que obtiene el ExitStatus de este paso
                .from(validateAccountStep())
                    .on("INVALID").to(cancelTransactionStep())
                    .next(sendNotificationStep())

                // .end() -> Le indica a SpringBatch que el flujo ha finalizado
                .end()

                // .build() -> Construye el Job
                .build();
    }
}
