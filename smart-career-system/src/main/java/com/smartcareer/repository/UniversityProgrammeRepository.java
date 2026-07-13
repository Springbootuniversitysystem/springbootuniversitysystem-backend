package com.smartcareer.repository;

import com.smartcareer.entity.UniversityProgramme;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversityProgrammeRepository extends JpaRepository<UniversityProgramme, Long> {

    Page<UniversityProgramme> findByMinimumApsLessThanEqual(Integer userAps, Pageable pageable);
}