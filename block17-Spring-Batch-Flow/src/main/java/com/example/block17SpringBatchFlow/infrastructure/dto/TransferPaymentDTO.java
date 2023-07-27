package com.example.block17SpringBatchFlow.infrastructure.dto;

import lombok.Data;

@Data
public class TransferPaymentDTO {

    private Double availableBalance;
    private Double amountPaid;
    private Boolean isEnabled;
}
