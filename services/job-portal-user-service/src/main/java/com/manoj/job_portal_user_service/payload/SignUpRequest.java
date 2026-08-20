package com.manoj.job_portal_user_service.payload;

import com.manoj.job.domain.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SignUpRequest {
    @NotBlank(message = "Full Name is mandatory")
    private String fullName;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email Should be valid")
    private String email;

    @NotBlank(message = "Password is Mandatory")
    private String password;

    private String phone;

    @NotNull(message = "Role is Mandatory")
    private UserRole role;
}
