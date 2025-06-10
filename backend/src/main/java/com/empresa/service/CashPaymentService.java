package com.empresa.service;

import com.empresa.dto.request.CashPaymentRequestDTO;
import com.empresa.dto.response.CashPaymentResponseDTO;

public interface CashPaymentService {
    CashPaymentResponseDTO createCashPayment(CashPaymentRequestDTO paymentDTO);
    CashPaymentResponseDTO getCashPaymentById(String id);
}