package com.empresa.mapper;

import com.empresa.dto.request.BillDetailRequestDTO;
import com.empresa.dto.response.BillDetailResponseDTO;
import com.empresa.model.BillDetail;
import com.empresa.model.BillDetail.BillDetailId;
import com.empresa.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface BillDetailMapper {

    // Convertir DTO de request a entidad
    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "bill", ignore = true),
        @Mapping(target = "product", source = "productId", qualifiedByName = "mapProduct"),
        @Mapping(target = "quantity", source = "quantity"),
        @Mapping(target = "subTotal", source = "subTotal")
    })
    BillDetail toEntity(BillDetailRequestDTO dto);

    @Mapping(target = "productId", source = "product.idProduct")
    @Mapping(target = "productName", source = "product.name")
    @Mapping(target = "unitPrice", source = "product.price")
    @Mapping(target = "quantity", source = "quantity")
    @Mapping(target = "subTotal", expression = "java(entity.getUnitPrice().multiply(new java.math.BigDecimal(entity.getQuantity())))")
    BillDetailResponseDTO toDto(BillDetail entity);

    default String map(BillDetailId id) {
        return id != null ? id.toString() : null; // O algún campo específico del ID
    }


    // Método auxiliar para mapear productId a Product
    @Named("mapProduct")
    default Product mapProduct(String productId) {
        if (productId == null) return null;
        Product product = new Product();
        product.setIdProduct(productId);
        return product;
    }
}
