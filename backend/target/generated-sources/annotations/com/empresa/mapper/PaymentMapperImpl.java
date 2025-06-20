package com.empresa.mapper;

import com.empresa.dto.request.PaymentRequestDTO;
import com.empresa.dto.response.PaymentResponseDTO;
import com.empresa.model.Bill;
import com.empresa.model.Payment;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-17T10:04:05-0500",
    comments = "version: 1.6.1, compiler: javac, environment: Java 17.0.15 (Eclipse Adoptium)"
)
@Component
public class PaymentMapperImpl extends PaymentMapper {

    @Override
    public Payment toEntity(PaymentRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Payment payment = new Payment();

        payment.setBill( mapBill( dto.getBillId() ) );
        payment.setTransferPayment( mapTransferPayment( dto ) );
        payment.setCashPayment( mapCashPayment( dto ) );
        payment.setDate( dto.getDate() );
        payment.setAmountPaid( dto.getAmountPaid() );
        payment.setPaymentMethod( dto.getPaymentMethod() );

        return payment;
    }

    @Override
    public PaymentResponseDTO toDto(Payment payment) {
        if ( payment == null ) {
            return null;
        }

        PaymentResponseDTO paymentResponseDTO = new PaymentResponseDTO();

        paymentResponseDTO.setBillId( paymentBillId( payment ) );
        paymentResponseDTO.setPaymentDetails( mapPaymentDetails( payment ) );
        paymentResponseDTO.setId( payment.getId() );
        paymentResponseDTO.setDate( payment.getDate() );
        paymentResponseDTO.setAmountPaid( payment.getAmountPaid() );
        paymentResponseDTO.setPaymentMethod( payment.getPaymentMethod() );

        paymentResponseDTO.setBillReference( getBillReference(payment.getBill()) );

        return paymentResponseDTO;
    }

    private Integer paymentBillId(Payment payment) {
        Bill bill = payment.getBill();
        if ( bill == null ) {
            return null;
        }
        return bill.getId();
    }
}
