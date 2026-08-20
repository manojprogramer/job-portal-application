package com.manoj.job_portal_user_service.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginRequest {
    @Email(message = "Email Should Be Valid")
    @NotBlank(message = "Email is mandatory")
    private String email;

    @NotBlank(message = "Password is Mandatory")
    private String password;
}
