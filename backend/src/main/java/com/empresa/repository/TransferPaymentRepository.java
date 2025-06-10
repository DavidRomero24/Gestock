package com.empresa.repository;

import com.empresa.model.TransferPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransferPaymentRepository extends JpaRepository<TransferPayment, String> {

    @Query("SELECT t FROM TransferPayment t WHERE t.originBank = :bankName")
    List<TransferPayment> findByOriginBank(String bankName);

    @Query("SELECT t FROM TransferPayment t WHERE t.accountNumber = :accountNumber")
    List<TransferPayment> findByAccountNumber(String accountNumber);

    @Query("SELECT t FROM TransferPayment t WHERE t.transferCode = :transferCode")
    Optional<TransferPayment> findByTransferCode(String transferCode);
}