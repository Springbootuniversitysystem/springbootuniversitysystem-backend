package com.smartcareer.service;

import com.smartcareer.dto.ForgotPasswordRequestDTO;
import com.smartcareer.dto.ResetPasswordRequestDTO;
import com.smartcareer.dto.VerifyResetCodeRequestDTO;
import com.smartcareer.entity.ContactMessage;
import com.smartcareer.entity.Learner;
import com.smartcareer.entity.PasswordResetToken;
import com.smartcareer.entity.User;
import com.smartcareer.exception.CodeUsedException;
import com.smartcareer.exception.ExpiredCodeException;
import com.smartcareer.exception.InvalidCodeException;
import com.smartcareer.exception.LearnerNotFoundException;
import com.smartcareer.repository.LearnerRepository;
import com.smartcareer.repository.PasswordResetTokenRepository;
import com.smartcareer.repository.UserRepository;
import com.smartcareer.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PasswordResetServiceImpl implements  PasswordResetService{


    private final LearnerRepository learnerRepository;
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private  final  EmailService emailService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

        @Override
        public Response<String> forgotPassword(ForgotPasswordRequestDTO request) {

            Learner learner = learnerRepository
                    .findByEmail(request.getEmail())
                    .orElseThrow(() ->
                            new LearnerNotFoundException("No account found with this email."));


            String verificationCode = Helper.generateVerificationCode();

            PasswordResetToken resetToken = PasswordResetToken.builder()
                    .token(verificationCode)
                    .learner(learner)
                    .expiryDate(LocalDateTime.now().plusMinutes(15))
                    .used(false)
                    .build();

            passwordResetTokenRepository.save(resetToken);


            //Create a message
            ContactMessage message = new ContactMessage();
            message.setSubject("Reset password");
            message.setName(learner.getFirstName());
            message.setEmailAddress(request.getEmail());
            message.setMessage(
                    "Hello " + learner.getFirstName() + ",\n\n" +
                            "We received a request to reset your SmartCareer account password.\n\n" +
                            "Your verification code is:\n\n" +
                            verificationCode + "\n\n" +
                            "This code expires in 15 minutes.\n\n" +
                            "If you did not request a password reset, you can safely ignore this email.\n\n" +
                            "Kind regards,\n" +
                            "The SmartCareer Team");
            message.setSubmittedAt(LocalDateTime.now());

            emailService.sendContactMessage(message);


            return Response.success(null,"Password reset request received.");
        }

    @Override
    public Response<String> verifyResetCode(VerifyResetCodeRequestDTO request) {
        Learner learner = learnerRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new LearnerNotFoundException("Learner not found."));

        PasswordResetToken token =
                passwordResetTokenRepository
                        .findTopByLearnerOrderByExpiryDateDesc(learner)
                        .orElseThrow(() ->
                                new RuntimeException("Verification code not found."));


        if (token.isUsed()) {
            throw new CodeUsedException("Verification code has already been used.");
        }

        if (token.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new ExpiredCodeException("Verification code has expired.");
        }

        if (!token.getToken().equals(request.getCode())) {
            throw new InvalidCodeException("Invalid verification code.");
        }

        return Response.success(null, "Verification successful.");
    }

    @Override
    public Response<String> resetPassword(ResetPasswordRequestDTO request) {
        Learner learner = learnerRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new LearnerNotFoundException("Learner not found."));

        PasswordResetToken token = passwordResetTokenRepository
                .findByLearnerAndToken(learner, request.getCode())
                .orElseThrow(() ->
                        new RuntimeException("Invalid verification code."));

        if (token.isUsed()) {
            throw new RuntimeException("Verification code already used.");
        }

        if (token.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Verification code expired.");
        }

        User user = learner.getUser();

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        userRepository.save(user);


        token.setUsed(true);

        passwordResetTokenRepository.save(token);

        return Response.success(null, "Password reset successfully.");
    }

}
