package com.empresa.service;

import com.empresa.dto.request.TransferPaymentRequestDTO;
import com.empresa.dto.response.TransferPaymentResponseDTO;
import java.util.List;
import java.math.BigDecimal;

public interface TransferPaymentService {
    TransferPaymentResponseDTO createTransferPayment(TransferPaymentRequestDTO paymentDTO);
    TransferPaymentResponseDTO getTransferPaymentById(String id);
    TransferPaymentResponseDTO getTransferByCode(String transferCode);
    List<TransferPaymentResponseDTO> getTransfersByBank(String bankName);
    List<TransferPaymentResponseDTO> getTransfersByAccount(String accountNumber);
    BigDecimal getTotalTransfersByBank(String bankName);
}