package com.smartcareer.service;

import com.smartcareer.entity.Bursary;
import com.smartcareer.exception.ResourceNotFoundException;
import com.smartcareer.repository.BursaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BursaryService {

    private final BursaryRepository bursaryRepository;

    @Transactional(readOnly = true)
    public Page<Bursary> getAllBursaries(Pageable pageable) {
        return bursaryRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Page<Bursary> getEligibleBursaries(Integer userAps, Pageable pageable) {
        return bursaryRepository.findByRequiredApsLessThanEqual(userAps, pageable);
    }

    @Transactional
    public Bursary createBursary(Bursary bursary) {
        return bursaryRepository.save(bursary);
    }

    @Transactional
    public Bursary updateBursary(Long id, Bursary updatedData) {
        Bursary existing = bursaryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bursary asset not found with ID: " + id));

        existing.setProviderName(updatedData.getProviderName());
        existing.setTitle(updatedData.getTitle());
        existing.setRequiredAps(updatedData.getRequiredAps());
        existing.setFieldOfStudy(updatedData.getFieldOfStudy());
        existing.setCoverageDetails(updatedData.getCoverageDetails());
        existing.setDeadline(updatedData.getDeadline());

        return bursaryRepository.save(existing);
    }

    @Transactional
    public void deleteBursary(Long id) {
        if (!bursaryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Bursary asset not found with ID: " + id);
        }
        bursaryRepository.deleteById(id);
    }
}