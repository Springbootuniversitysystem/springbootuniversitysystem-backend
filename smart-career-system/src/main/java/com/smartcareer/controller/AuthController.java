package com.smartcareer.controller;

import com.smartcareer.dto.LoginRequestDTO;
import com.smartcareer.dto.LoginResponseDTO;
import com.smartcareer.dto.RegisterRequestDTO;
import com.smartcareer.response.Response;
import com.smartcareer.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/learners")
@Validated
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Response<LoginResponseDTO>> login (@Valid @RequestBody LoginRequestDTO loginRequestDTO)
    {
        Response<LoginResponseDTO> loginResponseDTOResponse = authService.login(loginRequestDTO);

        return ResponseEntity.ok(loginResponseDTOResponse);
    }

    @PostMapping("/register")
    public  ResponseEntity<Response<Void>> register(@Valid @RequestBody RegisterRequestDTO registerRequestDTO)
    {
        Response<Void> response = authService.register(registerRequestDTO);

        return ResponseEntity.ok(response);
    }




}
