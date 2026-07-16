package com.smartcareer.service;

import com.smartcareer.entity.ApplicationStatus;
import com.smartcareer.entity.Learner;
import com.smartcareer.entity.ProgrammeApplication;
import com.smartcareer.entity.UniversityProgramme;
import com.smartcareer.exception.ResourceNotFoundException;
import com.smartcareer.repository.LearnerRepository;
import com.smartcareer.repository.ProgrammeApplicationRepository;
import com.smartcareer.repository.UniversityProgrammeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProgrammeApplicationService {

    private final ProgrammeApplicationRepository applicationRepository;
    private final LearnerRepository learnerRepository;
    private final UniversityProgrammeRepository programmeRepository;

    public ProgrammeApplication saveProgrammeForLearner(Long learnerId, Long programmeId) {
        // 1. Verify the learner exists
        Learner learner = learnerRepository.findById(learnerId)
                .orElseThrow(() -> new ResourceNotFoundException("Learner not found"));

        // 2. Verify the programme exists
        UniversityProgramme programme = programmeRepository.findById(programmeId)
                .orElseThrow(() -> new ResourceNotFoundException("Programme not found"));

        // 3. Prevent duplicates
        if (applicationRepository.existsByLearnerIdAndUniversityProgrammeId(learnerId, programmeId)) {
            throw new IllegalStateException("Programme is already saved to your tracker.");
        }

        // 4. Create and save the new application link
        ProgrammeApplication application = new ProgrammeApplication();
        application.setLearner(learner);
        application.setUniversityProgramme(programme);
        application.setStatus(ApplicationStatus.SAVED);
        application.setDateSaved(LocalDate.now());

        return applicationRepository.save(application);
    }

    public List<ProgrammeApplication> getLearnerApplications(Long learnerId) {
        return applicationRepository.findByLearnerId(learnerId);
    }
    // NEW: Update application status (e.g., SAVED to APPLIED)
    public ProgrammeApplication updateApplicationStatus(Long applicationId, String status) {
        // NOTE: Ensure your repository is named 'applicationRepository'. Adjust if it is named something else like 'repository'.
        ProgrammeApplication application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        // If your status in the entity is an Enum instead of a String, use ApplicationStatus.valueOf(status) here instead.
        ApplicationStatus.valueOf(status);
        return applicationRepository.save(application);
    }

    // NEW: Delete an application
    public void deleteApplication(Long applicationId) {
        ProgrammeApplication application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        applicationRepository.delete(application);
    }
}