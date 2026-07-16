package com.smartcareer.service;

import com.smartcareer.entity.UniversityProgramme;
import com.smartcareer.repository.UniversityProgrammeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<UniversityProgramme> getAllProgrammes() {
        return repository.findAll();
    }

    public UniversityProgramme updateProgramme(Long id, UniversityProgramme updatedData) {
        UniversityProgramme existingProgramme = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Programme not found with id: " + id));

        // FIXED: Now using the exact variables from your Entity
        existingProgramme.setInstitutionName(updatedData.getInstitutionName());
        existingProgramme.setFaculty(updatedData.getFaculty());
        existingProgramme.setProgrammeName(updatedData.getProgrammeName());
        existingProgramme.setMinimumAps(updatedData.getMinimumAps());
        existingProgramme.setDescription(updatedData.getDescription());
        existingProgramme.setApplicationDeadline(updatedData.getApplicationDeadline());

        return repository.save(existingProgramme);
    }

    public void deleteProgramme(Long id) {
        UniversityProgramme programme = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Programme not found with id: " + id));
        repository.delete(programme);
    }

}