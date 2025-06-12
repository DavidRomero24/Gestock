package com.empresa.mapper;

import com.empresa.dto.request.StaffRequestDTO;
import com.empresa.dto.response.StaffResponseDTO;
import com.empresa.model.Staff;
import org.mapstruct.*;
import org.springframework.util.StringUtils;


@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StaffMapper {

    @Mapping(target = "bills", ignore = true)
    @Mapping(target = "status", ignore = true) // El status se maneja por separado
    Staff toEntity(StaffRequestDTO dto);

    @Mapping(target = "fullName", expression = "java(generateFullName(staff))")
    // @Mapping(target = "fullName", expression = "java("
    //     + "staff.getName1() + ' ' + "
    //     + "(staff.getName2() != null ? staff.getName2() + ' ' : '') + "
    //     + "staff.getLastName1() + ' ' + "
    //     + "(staff.getLastName2() != null ? staff.getLastName2() : '')"
    //     + ")")
    StaffResponseDTO toDto(Staff staff);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(StaffRequestDTO dto, @MappingTarget Staff staff);

    default String generateFullName(Staff staff) {
        StringBuilder fullName = new StringBuilder();
        fullName.append(staff.getName1());
        
        if (StringUtils.hasText(staff.getName2())) {
            fullName.append(" ").append(staff.getName2());
        }
        
        fullName.append(" ").append(staff.getLastName());
        
        if (StringUtils.hasText(staff.getLastName2())) {
            fullName.append(" ").append(staff.getLastName2());
        }
        
        return fullName.toString();
    }


    @AfterMapping
    default void setTypeStaffUpperCase(@MappingTarget Staff staff) {
        if (staff.getTypeStaff() != null) {
            staff.setTypeStaff(staff.getTypeStaff().toUpperCase());
        }
    }
}