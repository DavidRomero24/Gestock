package com.empresa.mapper;

import com.empresa.dto.request.SupplierRequestDTO;
import com.empresa.dto.response.SupplierResponseDTO;
import com.empresa.model.Supplier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
// import org.mapstruct.Named;
import org.springframework.util.StringUtils;

@Mapper(componentModel = "spring")
public interface SupplierMapper {

    @Mapping(target = "city", ignore = true) // Se maneja en el servicio
    Supplier toEntity(SupplierRequestDTO dto);

    // @Mapping(target = "fullName", expression = "java(supplier.getName1() + ' ' + supplier.getName2() + ' ' + supplier.getLastName1() + ' ' + supplier.getLastName2())")
    @Mapping(target = "city", source = "city.nameCity")
    @Mapping(target = "department", source = "city.department.name")
    @Mapping(target = "fullName", source = ".", qualifiedByName = "fullNameGenerator")

    SupplierResponseDTO toDto(Supplier supplier);

    @Named("fullNameGenerator")
    default String generateFullName(Supplier supplier) {
        StringBuilder fullName = new StringBuilder();
        fullName.append(supplier.getName1());
        
        if (StringUtils.hasText(supplier.getName2())) {
            fullName.append(" ").append(supplier.getName2());
        }
        
        fullName.append(" ").append(supplier.getLastName1());
        
        if (StringUtils.hasText(supplier.getLastName2())) {
            fullName.append(" ").append(supplier.getLastName2());
        }
        
        return fullName.toString();
    }
}