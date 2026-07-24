package com.smartcareer.repository;

import com.smartcareer.entity.Learner;
import com.smartcareer.entity.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    Optional<PasswordResetToken> findByToken(String token);
    Optional<PasswordResetToken> findTopByLearnerOrderByExpiryDateDesc(Learner learner);
    Optional<PasswordResetToken> findByLearnerAndToken(Learner learner, String token);
}
