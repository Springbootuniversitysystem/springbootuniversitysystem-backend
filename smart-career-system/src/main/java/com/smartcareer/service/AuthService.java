package com.smartcareer.service;

import com.smartcareer.dto.LoginRequestDTO;
import com.smartcareer.dto.LoginResponseDTO;
import com.smartcareer.dto.RegisterRequestDTO;
import com.smartcareer.response.Response;

public interface AuthService {

    Response<Void> register(RegisterRequestDTO registerRequestDTO);
    Response<LoginResponseDTO> login(LoginRequestDTO requestDTO);
}
