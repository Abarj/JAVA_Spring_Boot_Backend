package com.example.block17SpringBatchFlow.infrastructure.controller;

import com.example.block17SpringBatchFlow.entity.TransferPaymentEntity;
import com.example.block17SpringBatchFlow.infrastructure.dto.TransferPaymentDTO;
import com.example.block17SpringBatchFlow.infrastructure.repository.TransferPaymentRepository;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/v1/payment")
public class PaymentController {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    @Autowired
    private TransferPaymentRepository transferPaymentRepository;

    @PostMapping("/transfer")
    public ResponseEntity<?> transferPayment(@RequestBody TransferPaymentDTO transferPaymentDTO) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {

        // Generamos un id único que siempre es diferente
        String transactionId = UUID.randomUUID().toString();

        TransferPaymentEntity transferPayment = TransferPaymentEntity.builder()
                .transactionId(transactionId)
                .availableBalance(transferPaymentDTO.getAvailableBalance())
                .amountPaid(transferPaymentDTO.getAmountPaid())
                .isEnabled(transferPaymentDTO.getIsEnabled())
                .isProcessed(false)
                .build();

        transferPaymentRepository.save(transferPayment);

        // Creamos los JobParameters -> Se puede acceder a estos parámetros desde todos los Tasklets
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("id", UUID.randomUUID().toString())
                .addString("transactionId", transactionId)
                .toJobParameters();

        jobLauncher.run(job, jobParameters);

        Map<String, Object> httpResponse = new HashMap<>();
        httpResponse.put("TransactionId", transactionId);
        httpResponse.put("Message", "Received transaction");

        return ResponseEntity.ok(httpResponse);
    }
}
