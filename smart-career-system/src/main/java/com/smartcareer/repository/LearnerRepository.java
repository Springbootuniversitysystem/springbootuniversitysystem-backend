package com.smartcareer.repository;

import com.smartcareer.entity.Learner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LearnerRepository  extends JpaRepository<Learner,Long> {

    Optional<Learner>  findByEmail(String email);
    Optional<Learner> findByStudentId(String studentId);

}
