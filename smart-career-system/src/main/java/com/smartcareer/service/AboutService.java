package com.smartcareer.service;

import com.smartcareer.dto.PlatformStatsDTO;
import com.smartcareer.repository.LearnerRepository;
import com.smartcareer.repository.UniversityProgrammeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AboutService {

    private final LearnerRepository learnerRepository;
    private final UniversityProgrammeRepository programmeRepository;

    public PlatformStatsDTO getLivePlatformStats() {
        return PlatformStatsDTO.builder()
                .totalProvinces(9) // Hardcoded because there are exactly 9 provinces
                .totalProgrammes(programmeRepository.count()) // Live count of courses from database
                .totalStudents(learnerRepository.count())     // Live count of students from database
                .build();
    }
}