package com.empresa.repository;

import com.empresa.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.math.BigDecimal;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    @Query("SELECT p FROM Payment p WHERE p.bill.id = :billId ORDER BY p.date DESC")
    List<Payment> findByBillId(Integer billId);

    @Query("SELECT p FROM Payment p WHERE p.paymentMethod = :method")
    List<Payment> findByPaymentMethod(String method);

    @Query("SELECT p FROM Payment p WHERE p.date BETWEEN :startDate AND :endDate")
    List<Payment> findByDateRange(LocalDate startDate, LocalDate endDate);

    @Query("SELECT SUM(p.amountPaid) FROM Payment p WHERE p.bill.id = :billId")
    BigDecimal getTotalPaidByBillId(Integer billId);

    @Query("SELECT p FROM Payment p WHERE p.transferPayment.id = :transferId")
    List<Payment> findByTransferId(String transferId);

    @Query("SELECT p FROM Payment p WHERE p.cashPayment.id = :cashPaymentId")
    List<Payment> findByCashPaymentId(String cashPaymentId);
}