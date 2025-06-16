package com.empresa.service.impl;

import com.empresa.dto.request.LoginRequestDTO;
import com.empresa.dto.request.RegisterRequestDTO;
import com.empresa.dto.response.AuthResponseDTO;
import com.empresa.mapper.UserMapper;
import com.empresa.model.User;
import com.empresa.repository.UserRepository;
import com.empresa.security.JwtService;
import com.empresa.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthResponseDTO login(LoginRequestDTO loginRequest) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsername(),
                    loginRequest.getPassword()
                )
            );

            UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());

            String jwtToken = jwtService.generateToken(userDetails);
            String refreshToken = jwtService.generateRefreshToken(userDetails);
            Date expirationDate = jwtService.extractExpiration(jwtToken);

            return new AuthResponseDTO(
                jwtToken,
                refreshToken,
                userDetails.getUsername(),
                userDetails.getAuthorities().toString(),
                expirationDate
            );

        } catch (BadCredentialsException e) {
            throw new RuntimeException("Credenciales inválidas", e);
        }
    }

    @Override
    public AuthResponseDTO refreshToken(String refreshToken) {
        String username = jwtService.extractUsername(refreshToken);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if (jwtService.isTokenValid(refreshToken, userDetails)) {
            String newToken = jwtService.generateToken(userDetails);
            Date expirationDate = jwtService.extractExpiration(newToken);

            return new AuthResponseDTO(
                newToken,
                refreshToken,
                userDetails.getUsername(),
                userDetails.getAuthorities().toString(),
                expirationDate
            );
        }
        throw new RuntimeException("Refresh token inválido");
    }

    @Override
    public AuthResponseDTO register(RegisterRequestDTO request) {
        // Validar si el usuario ya existe
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("El usuario ya está registrado");
        }

        // Validar coincidencia de contraseñas
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new RuntimeException("Las contraseñas no coinciden");
        }

        // Mapear DTO a entidad
        User user = UserMapper.toEntity(request);

        // Encriptar contraseña
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // Guardar el usuario
        userRepository.save(user);

        // Generar tokens
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        String jwtToken = jwtService.generateToken(userDetails);
        String refreshToken = jwtService.generateRefreshToken(userDetails);
        Date expirationDate = jwtService.extractExpiration(jwtToken);

        return new AuthResponseDTO(
            jwtToken,
            refreshToken,
            user.getUsername(),
            user.getRole().name(),
            expirationDate
        );
    }
}
