package com.smartcareer.repository;

import com.smartcareer.entity.ProgrammeApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgrammeApplicationRepository extends JpaRepository<ProgrammeApplication, Long> {

    // Finds all saved applications for a specific learner
    List<ProgrammeApplication> findByLearnerId(Long learnerId);

    // Checks if a learner already saved a specific programme so they don't duplicate it
    boolean existsByLearnerIdAndUniversityProgrammeId(Long learnerId, Long programmeId);
}