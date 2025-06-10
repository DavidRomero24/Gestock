package com.empresa.mapper;

import com.empresa.dto.request.ProductRequestDTO;
import com.empresa.dto.response.ProductResponseDTO;
import com.empresa.model.Product;
import org.mapstruct.*;
// import org.springframework.util.StringUtils;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {

    @Mapping(target = "idProduct", ignore = true)
    @Mapping(target = "billDetails", ignore = true)
    Product toEntity(ProductRequestDTO dto);

    @Mapping(target = "stockStatus", expression = "java(getStockStatus(product))")
    ProductResponseDTO toDto(Product product);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(ProductRequestDTO dto, @MappingTarget Product entity);

    default String getStockStatus(Product product) {
        if (product.getStock() == null || product.getStock() <= 0) {
            return "SIN_STOCK";
        } else if (product.getStock() < 10) { // Umbral para bajo stock
            return "BAJO_STOCK";
        } else {
            return "EN_STOCK";
        }
    }

    @AfterMapping
    default void setCategoryUpperCase(@MappingTarget Product product) {
        if (product.getCategory() != null) {
            product.setCategory(product.getCategory().toUpperCase());
        }
    }
}