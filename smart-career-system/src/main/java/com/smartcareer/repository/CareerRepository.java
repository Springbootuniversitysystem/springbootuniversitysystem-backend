package com.smartcareer.repository;

import com.smartcareer.entity.Career;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CareerRepository extends JpaRepository<Career,Long> {

    Page<Career> findByCareerNameContainingIgnoreCase(String careerName, Pageable pageable);

    boolean existsByCareerName(String careerName);
}
