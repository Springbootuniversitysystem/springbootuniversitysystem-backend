package com.smartcareer.service;

import com.smartcareer.entity.UniversityProgramme;
import com.smartcareer.repository.UniversityProgrammeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UniversityProgrammeService {

    private final UniversityProgrammeRepository repository;

    public Page<UniversityProgramme> getEligibleProgrammes(Integer userAps, Pageable pageable) {
        return repository.findByMinimumApsLessThanEqual(userAps, pageable);
    }

    public UniversityProgramme saveProgramme(UniversityProgramme programme) {
        return repository.save(programme);
    }
}