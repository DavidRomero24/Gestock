package com.empresa.mapper;

import com.empresa.dto.request.PaymentRequestDTO;
import com.empresa.dto.response.PaymentResponseDTO;
import com.empresa.model.Bill;
import com.empresa.model.Payment;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-10T08:42:12-0500",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 3.42.0.v20250514-1000, environment: Java 21.0.7 (Eclipse Adoptium)"
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
        payment.setAmountPaid( dto.getAmountPaid() );
        payment.setDate( dto.getDate() );
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
        paymentResponseDTO.setAmountPaid( payment.getAmountPaid() );
        paymentResponseDTO.setDate( payment.getDate() );
        paymentResponseDTO.setId( payment.getId() );
        paymentResponseDTO.setPaymentMethod( payment.getPaymentMethod() );

        paymentResponseDTO.setBillReference( getBillReference(payment.getBill()) );

        return paymentResponseDTO;
    }

    private Integer paymentBillId(Payment payment) {
        if ( payment == null ) {
            return null;
        }
        Bill bill = payment.getBill();
        if ( bill == null ) {
            return null;
        }
        Integer id = bill.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
