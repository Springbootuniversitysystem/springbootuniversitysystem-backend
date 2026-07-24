package com.smartcareer.service;

import com.smartcareer.dto.ForgotPasswordRequestDTO;
import com.smartcareer.response.Response;

public interface PasswordResetService {

    Response<String> forgotPassword(ForgotPasswordRequestDTO request);
}
