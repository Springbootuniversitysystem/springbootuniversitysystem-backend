package com.smartcareer.repository;

import com.smartcareer.entity.Learner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LearnerRepository  extends JpaRepository<Learner,Long> {

}
