package com.example.block17SpringBatchFlow.batch;

import com.example.block17SpringBatchFlow.infrastructure.repository.TransferPaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class CancelTransactionTasklet implements Tasklet {

    @Autowired
    private TransferPaymentRepository transferPaymentRepository;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

        // Recuperamos el transactionId del jobParameters del PaymentController mediante chunkContext
        String transactionId = (String) chunkContext.getStepContext().getJobParameters().get("transactionId");

        // Recuperamos el error
        String errorMessage = chunkContext.getStepContext()
                .getStepExecution()
                .getJobExecution()
                .getExecutionContext().getString("message");

        log.info("------> Error, the transaction could not be performed due to the following reason: ".concat(errorMessage));

        // Actualizamos el estado de la transacción
        transferPaymentRepository.updateTransactionStatusError(true, errorMessage, transactionId);

        // Retornamos el RepeatStatus indicando que este paso ha sido finalizado
        return RepeatStatus.FINISHED;

        // Como no tenemos otro flujo no tenemos que hacer nada con el stepContribution
    }
}
