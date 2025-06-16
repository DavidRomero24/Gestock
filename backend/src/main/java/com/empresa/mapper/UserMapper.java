package com.empresa.mapper;

import com.empresa.dto.request.RegisterRequestDTO;
import com.empresa.model.User;
import com.empresa.model.enums.Role;

public class UserMapper {

    public static User toEntity(RegisterRequestDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword()); // ¡Recuerda encriptarlo después!
        user.setRole(dto.getRole() != null ? Role.valueOf(dto.getRole().toUpperCase()) : Role.USER);
        user.setFirstName(dto.getFirstName());
        user.setSecondName(dto.getSecondName());
        user.setLastName(dto.getLastName());
        user.setPhone(dto.getPhone());
        return user;
    }
}
