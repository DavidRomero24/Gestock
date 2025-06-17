package com.empresa.dto.request;

import jakarta.validation.constraints.NotBlank;


public class RegisterRequestDTO {
    
    
    @NotBlank(message = "Nombre es obligatorio")
    private String firstName;
    @NotBlank(message = "Segundo nombre es obligatorio")
    private String secondName;
    @NotBlank(message = "Apellido obligatorio")
    private String lastName;
    @NotBlank(message = "Usuario es obligatorio")
    private String username;
    private String email;
    private String phone;
    private String role;
    private String password;
    private String confirmPassword;

    public RegisterRequestDTO() {}

    public String getUsername() {
        return username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
