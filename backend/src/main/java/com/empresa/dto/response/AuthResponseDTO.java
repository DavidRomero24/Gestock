package com.empresa.dto.response;

import java.util.Date;


public class AuthResponseDTO {
    private String token;
    private String refreshToken;
    private String username;
    private String role;
    private Date expirationDate;

    // Constructores
    public AuthResponseDTO() {}

    public AuthResponseDTO(String token, String refreshToken, String username, String role, Date expirationDate) {
        this.token = token;
        this.refreshToken = refreshToken;
        this.username = username;
        this.role = role;
        this.expirationDate = expirationDate;
    }

    // Getters y Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}