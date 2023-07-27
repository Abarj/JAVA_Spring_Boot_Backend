package com.example.block17SpringBatchFlow.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "transfer_payment_history") // Historial de transacciones
public class TransferPaymentEntity {

    @Id
    @Column(name = "transaction_id", nullable = false)
    // Id de la transacción
    private String transactionId;

    @Column(name = "available_balance", nullable = false)
    // Saldo disponible del cliente en la cuenta
    private Double availableBalance;

    @Column(name = "amount_paid", nullable = false)
    // Total pagado
    private Double amountPaid;

    @Column(name = "is_enabled", nullable = false)
    // Estado de la cuenta (Activa/Inactiva)
    private Boolean isEnabled;

    @Column(name = "is_processed", nullable = false)
    // Estado de la transacción (Procesada/ No procesada)
    private Boolean isProcessed;

    // En caso de error -> Se guardará en el histórico de la transsación
    private String error;


}
