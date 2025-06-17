package com.empresa.service.impl;

import com.empresa.dto.request.LoginRequestDTO;
import com.empresa.dto.request.RegisterRequestDTO;
import com.empresa.dto.response.AuthResponseDTO;
import com.empresa.dto.exception.InvalidCredentialsException;
import com.empresa.dto.exception.InvalidRefreshTokenException;
import com.empresa.dto.exception.PasswordMismatchException;
import com.empresa.dto.exception.UserAlreadyExistsException;
import com.empresa.mapper.UserMapper;
import com.empresa.model.User;
import com.empresa.model.enums.Role;
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
            // Autenticar al usuario
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsername(),
                    loginRequest.getPassword()
                )
            );

            // Cargar detalles del usuario
            UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());

            // Generar tokens
            String jwtToken = jwtService.generateToken(userDetails);
            String refreshToken = jwtService.generateRefreshToken(userDetails);
            Date expirationDate = jwtService.extractExpiration(jwtToken);

            // Actualizar último login (si es necesario)
            updateLastLogin(loginRequest.getUsername());

            return new AuthResponseDTO(
                jwtToken,
                refreshToken,
                userDetails.getUsername(),
                userDetails.getAuthorities().toString(),
                expirationDate
            );

        } catch (BadCredentialsException e) {
            throw new InvalidCredentialsException("Usuario o contraseña incorrectos");
        }
    }

    @Override
    public AuthResponseDTO refreshToken(String refreshToken) {
        // Extraer username del token
        String username = jwtService.extractUsername(refreshToken);
        
        if (username == null) {
            throw new InvalidRefreshTokenException("Refresh token no contiene información de usuario");
        }

        // Cargar detalles del usuario
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        // Validar token
        if (!jwtService.isTokenValid(refreshToken, userDetails)) {
            throw new InvalidRefreshTokenException("Refresh token inválido o expirado");
        }

        // Generar nuevo token de acceso
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

    @Override
    public AuthResponseDTO register(RegisterRequestDTO request) {
        // Validar si el usuario ya existe
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("El usuario ya está registrado");
        }

        // Validar coincidencia de contraseñas
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new PasswordMismatchException("Las contraseñas no coinciden");
        }

        // Validar y establecer el rol
        Role userRole;
        try {
            userRole = Role.valueOf(request.getRole().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidCredentialsException("Rol no válido. Debe ser ADMIN, STAFF o USER");
        }

        // Mapear DTO a entidad
        User user = UserMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(userRole);


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

    private void updateLastLogin(String username) {
        userRepository.findByUsername(username).ifPresent(user -> {
            userRepository.updateLastLogin(username);
        });
    }
}