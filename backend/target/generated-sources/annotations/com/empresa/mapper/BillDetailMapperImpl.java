package com.empresa.mapper;

import com.empresa.dto.request.BillDetailRequestDTO;
import com.empresa.dto.response.BillDetailResponseDTO;
import com.empresa.model.BillDetail;
import com.empresa.model.Product;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-17T10:04:02-0500",
    comments = "version: 1.6.1, compiler: javac, environment: Java 17.0.15 (Eclipse Adoptium)"
)
@Component
public class BillDetailMapperImpl implements BillDetailMapper {

    @Override
    public BillDetail toEntity(BillDetailRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        BillDetail billDetail = new BillDetail();

        billDetail.setProduct( mapProduct( dto.getProductId() ) );
        billDetail.setQuantity( dto.getQuantity() );
        billDetail.setSubTotal( dto.getSubTotal() );

        return billDetail;
    }

    @Override
    public BillDetailResponseDTO toDto(BillDetail entity) {
        if ( entity == null ) {
            return null;
        }

        BillDetailResponseDTO billDetailResponseDTO = new BillDetailResponseDTO();

        billDetailResponseDTO.setProductId( entityProductIdProduct( entity ) );
        billDetailResponseDTO.setProductName( entityProductName( entity ) );
        billDetailResponseDTO.setUnitPrice( entityProductPrice( entity ) );
        billDetailResponseDTO.setQuantity( entity.getQuantity() );
        billDetailResponseDTO.setId( map( entity.getId() ) );

        billDetailResponseDTO.setSubTotal( entity.getUnitPrice().multiply(new java.math.BigDecimal(entity.getQuantity())) );

        return billDetailResponseDTO;
    }

    private String entityProductIdProduct(BillDetail billDetail) {
        Product product = billDetail.getProduct();
        if ( product == null ) {
            return null;
        }
        return product.getIdProduct();
    }

    private String entityProductName(BillDetail billDetail) {
        Product product = billDetail.getProduct();
        if ( product == null ) {
            return null;
        }
        return product.getName();
    }

    private BigDecimal entityProductPrice(BillDetail billDetail) {
        Product product = billDetail.getProduct();
        if ( product == null ) {
            return null;
        }
        return product.getPrice();
    }
}
