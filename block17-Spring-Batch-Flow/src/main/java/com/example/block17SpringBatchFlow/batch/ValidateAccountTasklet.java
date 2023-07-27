package com.example.block17SpringBatchFlow.batch;

import com.example.block17SpringBatchFlow.entity.TransferPaymentEntity;
import com.example.block17SpringBatchFlow.infrastructure.repository.TransferPaymentRepository;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidateAccountTasklet implements Tasklet {

    @Autowired
    private TransferPaymentRepository transferPaymentRepository;

    // ChunkContext es como una puerta de entrada al contexto de la ejecución, los pasos y toda la informacion acerda de nuestro flujo
    // StepContribution nos sirve para setear los estados de salida de cada step -> Es lo que ayuda a tomar decisiones acerda de nuestro job
    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

        Boolean filterIsApproved = true;

        // Recuperamos el transactionId del jobParameters del PaymentController mediante chunkContext
        String transactionId = (String) chunkContext.getStepContext().getJobParameters().get("transactionId");

        // Recuperamos el objeto TransferPaymentEntity
        TransferPaymentEntity transferPayment = transferPaymentRepository.findById(transactionId).orElseThrow();

        // Validar la transacción

        if(!transferPayment.getIsEnabled()) {
            // Error si el estado de la cuenta es inactiva
            chunkContext.getStepContext()
                    .getStepExecution()
                    .getJobExecution()
                    .getExecutionContext()
                    // Seteamos el mensaje de error en el contexto de Spring-Batch
                    .put("message", "The account is inactive");

            filterIsApproved = false;
        }

        if(transferPayment.getAmountPaid() > transferPayment.getAvailableBalance()) {
            // No se puede procesar por saldo insuficiente
            chunkContext.getStepContext()
                    .getStepExecution()
                    .getJobExecution()
                    .getExecutionContext()
                    // Seteamos el mensaje de error en el contexto de Spring-Batch
                    .put("message", "Insufficient balance");

            filterIsApproved = false;
        }

        // Si el filtro está aprobado se procesa el pago

        // Variable que almacena el estado de salida del tasklet para la toma de decisiones en el flujo de tareas
        ExitStatus exitStatus = null;

        if(filterIsApproved) {

            exitStatus = new ExitStatus("VALID");

            // Seteamos el exitStatus al stepContribution
            stepContribution.setExitStatus(exitStatus);
        } else {
            exitStatus = new ExitStatus("INVALID");

            // Seteamos el exitStatus al stepContribution
            stepContribution.setExitStatus(exitStatus);
        }

        return RepeatStatus.FINISHED;
    }
}
