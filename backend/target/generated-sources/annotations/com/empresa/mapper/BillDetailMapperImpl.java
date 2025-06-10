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
    date = "2025-06-10T08:36:25-0500",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 3.42.0.v20250514-1000, environment: Java 21.0.7 (Eclipse Adoptium)"
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

        billDetailResponseDTO.setProductName( entityProductName( entity ) );
        billDetailResponseDTO.setProductId( entityProductIdProduct( entity ) );
        billDetailResponseDTO.setUnitPrice( entityProductPrice( entity ) );
        billDetailResponseDTO.setQuantity( entity.getQuantity() );
        billDetailResponseDTO.setSubTotal( entity.getSubTotal() );

        return billDetailResponseDTO;
    }

    private String entityProductName(BillDetail billDetail) {
        if ( billDetail == null ) {
            return null;
        }
        Product product = billDetail.getProduct();
        if ( product == null ) {
            return null;
        }
        String name = product.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private String entityProductIdProduct(BillDetail billDetail) {
        if ( billDetail == null ) {
            return null;
        }
        Product product = billDetail.getProduct();
        if ( product == null ) {
            return null;
        }
        String idProduct = product.getIdProduct();
        if ( idProduct == null ) {
            return null;
        }
        return idProduct;
    }

    private BigDecimal entityProductPrice(BillDetail billDetail) {
        if ( billDetail == null ) {
            return null;
        }
        Product product = billDetail.getProduct();
        if ( product == null ) {
            return null;
        }
        BigDecimal price = product.getPrice();
        if ( price == null ) {
            return null;
        }
        return price;
    }
}
