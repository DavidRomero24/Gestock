package com.empresa.service;

import com.empresa.dto.request.PaymentRequestDTO;
import com.empresa.dto.response.PaymentResponseDTO;
import java.util.List;
import java.math.BigDecimal;
import java.time.LocalDate;

public interface PaymentService {
    PaymentResponseDTO createPayment(PaymentRequestDTO paymentDTO);
    PaymentResponseDTO getPaymentById(Integer id);
    List<PaymentResponseDTO> getPaymentsByBill(Integer billId);
    List<PaymentResponseDTO> getPaymentsByMethod(String method);
    List<PaymentResponseDTO> getPaymentsByDateRange(LocalDate start, LocalDate end);
    BigDecimal getTotalPaidByBill(Integer billId);
}