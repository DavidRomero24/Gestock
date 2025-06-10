package com.empresa.mapper;

import com.empresa.dto.request.CustomerRequestDTO;
import com.empresa.dto.response.CustomerResponseDTO;
import com.empresa.model.Customer;
import org.mapstruct.*;
import org.springframework.util.StringUtils;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerMapper {

    @Mapping(target = "id", ignore = true)
    Customer toEntity(CustomerRequestDTO dto);

    @Mapping(target = "fullName", expression = "java(generateFullName(entity))")
    CustomerResponseDTO toDto(Customer entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(CustomerRequestDTO dto, @MappingTarget Customer entity);

    default String generateFullName(Customer customer) {
        StringBuilder fullName = new StringBuilder();
        fullName.append(customer.getName1());
        
        if (StringUtils.hasText(customer.getName2())) {
            fullName.append(" ").append(customer.getName2());
        }
        
        fullName.append(" ").append(customer.getLastName1());
        
        if (StringUtils.hasText(customer.getLastName2())) {
            fullName.append(" ").append(customer.getLastName2());
        }
        
        return fullName.toString();
    }
}