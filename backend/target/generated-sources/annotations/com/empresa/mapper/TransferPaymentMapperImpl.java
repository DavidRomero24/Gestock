package com.empresa.mapper;

import com.empresa.dto.request.TransferPaymentRequestDTO;
import com.empresa.dto.response.TransferPaymentResponseDTO;
import com.empresa.model.TransferPayment;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-11T01:00:57-0500",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 3.42.0.v20250514-1000, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class TransferPaymentMapperImpl implements TransferPaymentMapper {

    @Override
    public TransferPayment toEntity(TransferPaymentRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        TransferPayment transferPayment = new TransferPayment();

        transferPayment.setOriginBank( dto.getOriginBank() );
        transferPayment.setAccountHolderName( dto.getAccountHolderName() );
        transferPayment.setAccountNumber( dto.getAccountNumber() );
        transferPayment.setTransferCode( dto.getTransferCode() );
        transferPayment.setAmount( dto.getAmount() );

        generateTransferCode( transferPayment );

        return transferPayment;
    }

    @Override
    public TransferPaymentResponseDTO toDto(TransferPayment entity) {
        if ( entity == null ) {
            return null;
        }

        TransferPaymentResponseDTO transferPaymentResponseDTO = new TransferPaymentResponseDTO();

        transferPaymentResponseDTO.setRelatedPayments( mapPayments( entity.getPayments() ) );
        transferPaymentResponseDTO.setAccountHolderName( entity.getAccountHolderName() );
        transferPaymentResponseDTO.setAccountNumber( entity.getAccountNumber() );
        transferPaymentResponseDTO.setAmount( entity.getAmount() );
        transferPaymentResponseDTO.setId( entity.getId() );
        transferPaymentResponseDTO.setOriginBank( entity.getOriginBank() );
        transferPaymentResponseDTO.setTransferCode( entity.getTransferCode() );

        return transferPaymentResponseDTO;
    }
}
