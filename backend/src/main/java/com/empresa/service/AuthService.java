package com.empresa.service;

import com.empresa.dto.request.LoginRequestDTO;
import com.empresa.dto.request.RegisterRequestDTO;
import com.empresa.dto.response.AuthResponseDTO;

public interface AuthService {
    AuthResponseDTO login(LoginRequestDTO loginRequest);
    AuthResponseDTO refreshToken(String refreshToken);
    AuthResponseDTO register(RegisterRequestDTO request);
}