package com.example.block17SpringBatchFlow.batch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

@Slf4j
public class SendNotificationTasklet implements Tasklet {

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

        // Recuperamos el transactionId del jobParameters del PaymentController mediante chunkContext
        String transactionId = (String) chunkContext.getStepContext().getJobParameters().get("transactionId");

        log.info("++++++ A notification has been sent to the client for the transaction: ".concat(transactionId) + " ++++++");

        // Retornamos el RepeatStatus indicando que este paso ha sido finalizado
        return RepeatStatus.FINISHED;

        // Como no tenemos otro flujo no tenemos que hacer nada con el stepContribution
    }
}
