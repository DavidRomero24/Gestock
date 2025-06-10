package com.empresa.dto.response;

import lombok.Data;

/**
 * DTO que contiene los detalles específicos de un pago, diferenciando entre
 * pagos en efectivo y transferencias bancarias.
 * 
 * Se utiliza como parte de PaymentResponseDTO para proporcionar información
 * detallada del método de pago utilizado.
 */
@Data
public class PaymentDetailDTO {
    
    /**
     * Detalles para pagos por transferencia
     */
    private String transferId;
    private String originBank;
    private String accountNumber;
    private String transferCode;
    
    /**
     * Detalles para pagos en efectivo
     */
    private String cashPaymentId;
    
    /**
     * Método de pago utilizado (para referencia rápida)
     */
    private String paymentMethod;
}

