package com.empresa.mapper;

import com.empresa.dto.request.PaymentRequestDTO;
import com.empresa.dto.response.PaymentDetailDTO;
import com.empresa.dto.response.PaymentResponseDTO;
import com.empresa.model.*;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class PaymentMapper {

    @Autowired
    protected CashPaymentMapper cashPaymentMapper;
    
    @Autowired
    protected TransferPaymentMapper transferPaymentMapper;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "bill", source = "billId", qualifiedByName = "mapBill")
    @Mapping(target = "transferPayment", source = "dto", qualifiedByName = "mapTransferPayment")
    @Mapping(target = "cashPayment", source = "dto", qualifiedByName = "mapCashPayment")
    public abstract Payment toEntity(PaymentRequestDTO dto);

    @Mapping(target = "billId", source = "bill.id")
    @Mapping(target = "billReference", expression = "java(getBillReference(payment.getBill()))")
    @Mapping(target = "paymentDetails", source = "payment", qualifiedByName = "mapPaymentDetails")
    public abstract PaymentResponseDTO toDto(Payment payment);

    @Named("mapBill")
    protected Bill mapBill(Integer billId) {
        if (billId == null) return null;
        Bill bill = new Bill();
        bill.setId(billId);
        return bill;
    }

    @Named("mapTransferPayment")
    protected TransferPayment mapTransferPayment(PaymentRequestDTO dto) {
        if (!"TRANSFERENCIA".equalsIgnoreCase(dto.getPaymentMethod())) {
            return null;
        }
        TransferPayment transfer = new TransferPayment();
        transfer.setOriginBank(dto.getOriginBank());
        transfer.setAccountHolderName(dto.getAccountHolderName());
        transfer.setAccountNumber(dto.getAccountNumber());
        transfer.setTransferCode(dto.getTransferCode());
        transfer.setAmount(dto.getAmountPaid());
        return transfer;
    }

    @Named("mapCashPayment")
    protected CashPayment mapCashPayment(PaymentRequestDTO dto) {
        if (!"EFECTIVO".equalsIgnoreCase(dto.getPaymentMethod())) {
            return null;
        }
        CashPayment cash = new CashPayment();
        cash.setAmount(dto.getAmountPaid());
        return cash;
    }

    @Named("mapPaymentDetails")
    protected PaymentDetailDTO mapPaymentDetails(Payment payment) {
        if (payment == null) {
            return null;
        }
        
        PaymentDetailDTO details = new PaymentDetailDTO();
        
        if (payment.getTransferPayment() != null) {
            details.setTransferId(payment.getTransferPayment().getId());
            details.setOriginBank(payment.getTransferPayment().getOriginBank());
            details.setAccountNumber(payment.getTransferPayment().getAccountNumber());
            details.setTransferCode(payment.getTransferPayment().getTransferCode());
            details.setPaymentMethod("TRANSFERENCIA");
        }
        
        if (payment.getCashPayment() != null) {
            details.setCashPaymentId(payment.getCashPayment().getId());
            details.setPaymentMethod("EFECTIVO");
        }
        
        return details;
    }

    protected String getBillReference(Bill bill) {
        return bill != null ? "FACT-" + bill.getId() : null;
    }
}