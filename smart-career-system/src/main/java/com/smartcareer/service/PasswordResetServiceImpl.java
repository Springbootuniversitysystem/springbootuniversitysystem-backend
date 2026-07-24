package com.smartcareer.service;

import com.smartcareer.dto.ForgotPasswordRequestDTO;
import com.smartcareer.entity.ContactMessage;
import com.smartcareer.entity.Learner;
import com.smartcareer.entity.PasswordResetToken;
import com.smartcareer.exception.LearnerNotFoundException;
import com.smartcareer.repository.LearnerRepository;
import com.smartcareer.repository.PasswordResetTokenRepository;
import com.smartcareer.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PasswordResetServiceImpl implements  PasswordResetService{


    private final LearnerRepository learnerRepository;
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private  final  EmailService emailService;

        @Override
        public Response<String> forgotPassword(ForgotPasswordRequestDTO request) {

            Learner learner = learnerRepository
                    .findByEmail(request.getEmail())
                    .orElseThrow(() ->
                            new LearnerNotFoundException("No account found with this email."));


            String token = Helper.generateToken();

            PasswordResetToken resetToken = PasswordResetToken.builder()
                    .token(token)
                    .learner(learner)
                    .expiryDate(LocalDateTime.now().plusMinutes(15))
                    .used(false)
                    .build();

            passwordResetTokenRepository.save(resetToken);

            String resetLink = "http://localhost:3000/reset-password?token=" + token;
            //Create a message
            ContactMessage message = new ContactMessage();
            message.setSubject("Reset password");
            message.setName(learner.getFirstName());
            message.setEmailAddress(request.getEmail());
            message.setMessage( "Hello " + learner.getFirstName() + ",\n\n" +
                    "We received a request to reset your SmartCareer account password.\n\n" +
                    "Your reset token is: " + token + "\n\n" +
                    "Or simply click the link below to reset your password:\n" +
                    resetLink + "\n\n" +
                    "This link will expire in 15 minutes.\n\n" +
                    "If you did not request a password reset, please ignore this email.\n\n" +
                    "Kind regards,\n" +
                    "The SmartCareer Team");
            message.setSubmittedAt(LocalDateTime.now());

            emailService.sendContactMessage(message);


            return Response.success(null,"Password reset request received.");
        }

}
