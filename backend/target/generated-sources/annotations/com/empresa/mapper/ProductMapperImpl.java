package com.empresa.mapper;

import com.empresa.dto.request.ProductRequestDTO;
import com.empresa.dto.response.ProductResponseDTO;
import com.empresa.model.Product;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-11T01:00:59-0500",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 3.42.0.v20250514-1000, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product toEntity(ProductRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Product product = new Product();

        product.setName( dto.getName() );
        product.setStock( dto.getStock() );
        product.setCategory( dto.getCategory() );
        product.setPrice( dto.getPrice() );
        product.setDescription( dto.getDescription() );

        setCategoryUpperCase( product );

        return product;
    }

    @Override
    public ProductResponseDTO toDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductResponseDTO productResponseDTO = new ProductResponseDTO();

        productResponseDTO.setCategory( product.getCategory() );
        productResponseDTO.setDescription( product.getDescription() );
        productResponseDTO.setIdProduct( product.getIdProduct() );
        productResponseDTO.setName( product.getName() );
        productResponseDTO.setPrice( product.getPrice() );
        productResponseDTO.setStock( product.getStock() );

        productResponseDTO.setStockStatus( getStockStatus(product) );

        return productResponseDTO;
    }

    @Override
    public void updateEntity(ProductRequestDTO dto, Product entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getName() != null ) {
            entity.setName( dto.getName() );
        }
        if ( dto.getStock() != null ) {
            entity.setStock( dto.getStock() );
        }
        if ( dto.getCategory() != null ) {
            entity.setCategory( dto.getCategory() );
        }
        if ( dto.getPrice() != null ) {
            entity.setPrice( dto.getPrice() );
        }
        if ( dto.getDescription() != null ) {
            entity.setDescription( dto.getDescription() );
        }

        setCategoryUpperCase( entity );
    }
}
