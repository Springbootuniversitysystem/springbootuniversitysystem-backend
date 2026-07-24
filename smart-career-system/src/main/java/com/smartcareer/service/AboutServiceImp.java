package com.smartcareer.service;

import com.smartcareer.dto.AboutPageDTO;
import com.smartcareer.dto.PlatformStatsDTO;
import com.smartcareer.entity.AboutPage;
import com.smartcareer.exception.AboutUsPageNotFoundException;
import com.smartcareer.repository.AboutPageRepository;
import com.smartcareer.repository.LearnerRepository;
import com.smartcareer.repository.UniversityProgrammeRepository;
import com.smartcareer.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AboutServiceImp implements  AboutService {

    private final LearnerRepository learnerRepository;
    private final UniversityProgrammeRepository programmeRepository;
    private final AboutPageRepository aboutPageRepository;

    //Update Destiney's getLivePlatformStats
    @Override
    public PlatformStatsDTO getLivePlatformStats() {
        return PlatformStatsDTO.builder()
                .totalProvinces(9) // Hardcoded because there are exactly 9 provinces
                .totalProgrammes(programmeRepository.count()) // Live count of courses from database
                .totalStudents(learnerRepository.count())     // Live count of students from database
                .build();
    }

    @Override
    public Response<AboutPageDTO> getAboutPage() {

        AboutPage aboutPage = aboutPageRepository.findById(1L)
                .orElseThrow(() -> new AboutUsPageNotFoundException("About page not found."));

        AboutPageDTO dto = Helper.mapAboutPageToDTO(aboutPage);

        // Add live statistics
        dto.setPlatformStats(getLivePlatformStats());

        return Response.success(dto, "About page retrieved successfully.");
    }

    @Override
    public Response<AboutPageDTO> updatePage(AboutPageDTO dto) {
        AboutPage aboutPage = aboutPageRepository.findById(1L)
                .orElseThrow(() -> new AboutUsPageNotFoundException("About page not found."));

        Helper.mapAboutPageFromDTO(aboutPage, dto);

        AboutPage savedPage = aboutPageRepository.save(aboutPage);

        AboutPageDTO response = Helper.mapAboutPageToDTO(savedPage);

        // Add live statistics
        response.setPlatformStats(getLivePlatformStats());

        return Response.success(response, "About page updated successfully.");
    }
}