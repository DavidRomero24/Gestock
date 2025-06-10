package com.empresa.service.impl;

import com.empresa.dto.request.CashPaymentRequestDTO;
import com.empresa.dto.response.CashPaymentResponseDTO;
import com.empresa.dto.exception.ResourceNotFoundException;
import com.empresa.mapper.CashPaymentMapper;
import com.empresa.model.CashPayment;
import com.empresa.repository.CashPaymentRepository;
import com.empresa.service.CashPaymentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CashPaymentServiceImpl implements CashPaymentService {

    private final CashPaymentRepository cashPaymentRepository;
    private final CashPaymentMapper cashPaymentMapper;

    public CashPaymentServiceImpl(CashPaymentRepository cashPaymentRepository,
                                CashPaymentMapper cashPaymentMapper) {
        this.cashPaymentRepository = cashPaymentRepository;
        this.cashPaymentMapper = cashPaymentMapper;
    }

    @Override
    @Transactional
    public CashPaymentResponseDTO createCashPayment(CashPaymentRequestDTO paymentDTO) {
        CashPayment payment = cashPaymentMapper.toEntity(paymentDTO);
        CashPayment savedPayment = cashPaymentRepository.save(payment);
        return cashPaymentMapper.toDto(savedPayment);
    }

    @Override
    @Transactional(readOnly = true)
    public CashPaymentResponseDTO getCashPaymentById(String id) {
        return cashPaymentRepository.findById(id)
                .map(cashPaymentMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Pago en efectivo no encontrado con ID: " + id));
    }
}