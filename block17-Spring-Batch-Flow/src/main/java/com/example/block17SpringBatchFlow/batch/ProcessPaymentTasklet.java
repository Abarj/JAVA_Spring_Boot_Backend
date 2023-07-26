package com.example.block17SpringBatchFlow.batch;

import com.example.block17SpringBatchFlow.infrastructure.repository.TransferPaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class ProcessPaymentTasklet implements Tasklet {

    @Autowired
    TransferPaymentRepository transferPaymentRepository;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

        // Recuperamos el transactionId del jobParameters del PaymentController mediante chunkContext
        String transactionId = (String) chunkContext.getStepContext().getJobParameters().get("transactionId");

        log.info("------> Payment of transaction {} processed successfully", transactionId);

        // Actualizamos el estado de la transacción
        transferPaymentRepository.updateTransactionStatus(true, transactionId);

        // Retornamos el RepeatStatus indicando que este paso ha sido finalizado
        return RepeatStatus.FINISHED;

        // Como no tenemos otro flujo no tenemos que hacer nada con el stepContribution
    }
}
