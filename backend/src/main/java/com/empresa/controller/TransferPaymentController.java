package com.empresa.controller;

import com.empresa.dto.request.TransferPaymentRequestDTO;
import com.empresa.dto.response.TransferPaymentResponseDTO;
import com.empresa.service.TransferPaymentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/transfer-payments")
public class TransferPaymentController {

    private final TransferPaymentService transferPaymentService;

    public TransferPaymentController(TransferPaymentService transferPaymentService) {
        this.transferPaymentService = transferPaymentService;
    }

    @PostMapping
    public ResponseEntity<TransferPaymentResponseDTO> createTransferPayment(
            @Valid @RequestBody TransferPaymentRequestDTO paymentDTO) {
        TransferPaymentResponseDTO createdPayment = transferPaymentService.createTransferPayment(paymentDTO);
        return new ResponseEntity<>(createdPayment, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransferPaymentResponseDTO> getTransferPaymentById(@PathVariable String id) {
        TransferPaymentResponseDTO payment = transferPaymentService.getTransferPaymentById(id);
        return ResponseEntity.ok(payment);
    }

    @GetMapping("/code/{transferCode}")
    public ResponseEntity<TransferPaymentResponseDTO> getTransferByCode(
            @PathVariable String transferCode) {
        TransferPaymentResponseDTO payment = transferPaymentService.getTransferByCode(transferCode);
        return ResponseEntity.ok(payment);
    }

    @GetMapping("/bank/{bankName}")
    public ResponseEntity<List<TransferPaymentResponseDTO>> getTransfersByBank(
            @PathVariable String bankName) {
        List<TransferPaymentResponseDTO> payments = transferPaymentService.getTransfersByBank(bankName);
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/account/{accountNumber}")
    public ResponseEntity<List<TransferPaymentResponseDTO>> getTransfersByAccount(
            @PathVariable String accountNumber) {
        List<TransferPaymentResponseDTO> payments = transferPaymentService.getTransfersByAccount(accountNumber);
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/bank/{bankName}/total")
    public ResponseEntity<BigDecimal> getTotalTransfersByBank(
            @PathVariable String bankName) {
        BigDecimal total = transferPaymentService.getTotalTransfersByBank(bankName);
        return ResponseEntity.ok(total);
    }
}