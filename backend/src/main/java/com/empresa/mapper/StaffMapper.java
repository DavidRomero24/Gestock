package com.empresa.mapper;

import com.empresa.dto.request.StaffRequestDTO;
import com.empresa.dto.response.StaffResponseDTO;
import com.empresa.model.Staff;
import org.mapstruct.*;
import org.springframework.util.StringUtils;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StaffMapper {

    @Mapping(target = "bills", ignore = true)
    @Mapping(target = "status", ignore = true) // El status se maneja por separado
    Staff toEntity(StaffRequestDTO dto);

    @Mapping(target = "fullName", expression = "java(generateFullName(entity))")
    @Mapping(target = "age", expression = "java(calculateAge(entity.getDateBirth()))")
    StaffResponseDTO toDto(Staff entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(StaffRequestDTO dto, @MappingTarget Staff entity);

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

    default Integer calculateAge(Date birthDate) {
        if (birthDate == null) return null;
        
        LocalDate birthLocalDate = birthDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
                
        return LocalDate.now().getYear() - birthLocalDate.getYear();
    }

    @AfterMapping
    default void setTypeStaffUpperCase(@MappingTarget Staff staff) {
        if (staff.getTypeStaff() != null) {
            staff.setTypeStaff(staff.getTypeStaff().toUpperCase());
        }
    }
}