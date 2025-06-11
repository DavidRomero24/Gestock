package com.empresa.mapper;

import com.empresa.dto.request.BillSupplyRequestDTO;
import com.empresa.dto.response.BillSupplyDetailResponseDTO;
import com.empresa.dto.response.BillSupplyResponseDTO;
import com.empresa.model.BillSupply;
import com.empresa.model.BillSupplyDetail;
import com.empresa.model.*;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface BillSupplyMapper {

    @Mapping(target = "idBillSupply", ignore = true)
    @Mapping(target = "date", expression = "java(new java.util.Date())")
    @Mapping(target = "total", ignore = true)
    @Mapping(target = "details", ignore = true)
    @Mapping(target = "supplier", source = "supplierId", qualifiedByName = "mapSupplier")
    BillSupply toEntity(BillSupplyRequestDTO dto);

    @Mapping(target = "supplierId", source = "supplier.id")
    @Mapping(target = "supplierName", expression = "java(getSupplierFullName(entity.getSupplier()))")
    @Mapping(target = "supplierCompany", source = "supplier.company")
    BillSupplyResponseDTO toDto(BillSupply entity);

    @Mapping(target = "productId", source = "product.idProduct")
    @Mapping(target = "productName", source = "product.name")
    BillSupplyDetailResponseDTO detailToDto(BillSupplyDetail detail);

    @Named("mapSupplier")
    default Supplier mapSupplier(String supplierId) {
        if (supplierId == null) return null;
        Supplier supplier = new Supplier();
        supplier.setId(supplierId);
        return supplier;
    }

    default String getSupplierFullName(Supplier supplier) {
        if (supplier == null) return null;
        return supplier.getName1() + " " + 
               (supplier.getName2() != null ? supplier.getName2() + " " : "") + 
               supplier.getLastName1() + 
               (supplier.getLastName2() != null ? " " + supplier.getLastName2() : "");
    }
}