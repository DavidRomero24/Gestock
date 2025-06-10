package com.empresa.service.impl;

import com.empresa.dto.request.TransferPaymentRequestDTO;
import com.empresa.dto.response.TransferPaymentResponseDTO;
import com.empresa.dto.exception.ResourceNotFoundException;
import com.empresa.mapper.TransferPaymentMapper;
import com.empresa.model.TransferPayment;
import com.empresa.repository.TransferPaymentRepository;
import com.empresa.service.TransferPaymentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransferPaymentServiceImpl implements TransferPaymentService {

    private final TransferPaymentRepository transferPaymentRepository;
    private final TransferPaymentMapper transferPaymentMapper;

    public TransferPaymentServiceImpl(TransferPaymentRepository transferPaymentRepository,
                                    TransferPaymentMapper transferPaymentMapper) {
        this.transferPaymentRepository = transferPaymentRepository;
        this.transferPaymentMapper = transferPaymentMapper;
    }

    @Override
    @Transactional
    public TransferPaymentResponseDTO createTransferPayment(TransferPaymentRequestDTO paymentDTO) {
        TransferPayment payment = transferPaymentMapper.toEntity(paymentDTO);
        TransferPayment savedPayment = transferPaymentRepository.save(payment);
        return transferPaymentMapper.toDto(savedPayment);
    }

    @Override
    @Transactional(readOnly = true)
    public TransferPaymentResponseDTO getTransferPaymentById(String id) {
        return transferPaymentRepository.findById(id)
                .map(transferPaymentMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Transferencia no encontrada con ID: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public TransferPaymentResponseDTO getTransferByCode(String transferCode) {
        return transferPaymentRepository.findByTransferCode(transferCode)
                .map(transferPaymentMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Transferencia no encontrada con código: " + transferCode));
    }

    @Override
    @Transactional(readOnly = true)
    public List<TransferPaymentResponseDTO> getTransfersByBank(String bankName) {
        return transferPaymentRepository.findByOriginBank(bankName).stream()
                .map(transferPaymentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TransferPaymentResponseDTO> getTransfersByAccount(String accountNumber) {
        return transferPaymentRepository.findByAccountNumber(accountNumber).stream()
                .map(transferPaymentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal getTotalTransfersByBank(String bankName) {
        return transferPaymentRepository.findByOriginBank(bankName).stream()
                .map(TransferPayment::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}