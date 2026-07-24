package com.smartcareer.controller;

import com.smartcareer.dto.ForgotPasswordRequestDTO;
import com.smartcareer.dto.ResetPasswordRequestDTO;
import com.smartcareer.dto.VerifyResetCodeRequestDTO;
import com.smartcareer.response.Response;
import com.smartcareer.service.PasswordResetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class PasswordResetController {

    private final PasswordResetService passwordResetService;

    @PostMapping("/forgot-password")
    public ResponseEntity<Response<String>> forgotPassword(
            @Valid @RequestBody ForgotPasswordRequestDTO request) {

        Response<String> response = passwordResetService.forgotPassword(request);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/verify-reset-code")
    public ResponseEntity<Response<String>> verifyResetCode(
            @Valid @RequestBody VerifyResetCodeRequestDTO request) {

        return ResponseEntity.ok(
                passwordResetService.verifyResetCode(request)
        );
    }

    @PostMapping("/reset-password")
    public ResponseEntity<Response<String>> resetPassword(
            @Valid @RequestBody ResetPasswordRequestDTO request) {

        return ResponseEntity.ok(
                passwordResetService.resetPassword(request)
        );
    }
}
