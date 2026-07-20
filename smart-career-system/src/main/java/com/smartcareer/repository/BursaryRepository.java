package com.smartcareer.repository;

import com.smartcareer.entity.Bursary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BursaryRepository extends JpaRepository<Bursary, Long> {
    Page<Bursary> findByRequiredApsLessThanEqual(Integer userAps, Pageable pageable);
    Page<Bursary> findByFieldOfStudyContainingIgnoreCase(String field, Pageable pageable);
}