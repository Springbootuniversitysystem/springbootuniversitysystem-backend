package com.smartcareer.service;

import com.smartcareer.dto.ForgotPasswordRequestDTO;
import com.smartcareer.dto.ResetPasswordRequestDTO;
import com.smartcareer.dto.VerifyResetCodeRequestDTO;
import com.smartcareer.response.Response;

public interface PasswordResetService {

    Response<String> forgotPassword(ForgotPasswordRequestDTO request);
    Response<String> verifyResetCode(VerifyResetCodeRequestDTO request);
    Response<String> resetPassword(ResetPasswordRequestDTO request);
}
