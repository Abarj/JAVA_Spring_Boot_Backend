package com.example.block17SpringBatchFlow.infrastructure.repository;

import com.example.block17SpringBatchFlow.entity.TransferPaymentEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TransferPaymentRepository extends CrudRepository<TransferPaymentEntity, String> {

    @Modifying
    @Transactional
    @Query("update TransferPaymentEntity tpe SET tpe.isProcessed = ?1 WHERE tpe.transactionId = ?2")
    // Actualizar el Status de la transacción
    void updateTransactionStatus(Boolean newValue, String transactionId);

    @Modifying
    @Transactional
    @Query("update TransferPaymentEntity tpe SET tpe.isProcessed = ?1, tpe.error = ?2 WHERE tpe.transactionId = ?3")
        // Actualizar el Status de la transacción en caso de Error
    void updateTransactionStatusError(Boolean newValue, String error, String transactionId);
}
