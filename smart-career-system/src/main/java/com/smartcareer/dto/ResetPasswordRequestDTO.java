package com.smartcareer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResetPasswordRequestDTO {

    @NotBlank(message = "Email is required")
    @Email
    private String email;

    @NotBlank(message = "Verification code is required")
    private String code;

    @NotBlank(message = "New password is required")
    private String newPassword;
}
