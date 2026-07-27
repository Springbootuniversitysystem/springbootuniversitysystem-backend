package com.smartcareer.service;

import com.smartcareer.dto.CareerAnalysisResponseDTO;
import com.smartcareer.dto.CourseRecommendationDTO;
import com.smartcareer.dto.SubjectMarkDTO;
import com.smartcareer.dto.UniversityProgrammeDTO;
import com.smartcareer.entity.ProgrammeSubjectRequirement;
import com.smartcareer.entity.UniversityProgramme;
import com.smartcareer.repository.UniversityProgrammeRepository;
import com.smartcareer.response.Response;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FilterProgrammesServiceImpl implements FilterProgrammesService {

    private final  ApsCalculatorService apsCalculatorService;
    private final UniversityProgrammeRepository programmeRepository;

    @Override
    public Response<CareerAnalysisResponseDTO> getQualifiedProgrammes(List<SubjectMarkDTO> learnerSubjects) {


            List<UniversityProgramme> programmes = programmeRepository.findAll();

            int learnerAps = apsCalculatorService.calculateTotalAps(learnerSubjects);

            List<UniversityProgrammeDTO> qualifiedProgrammes = new ArrayList<>();

            for (UniversityProgramme programme : programmes) {

                // 1. Check APS requirement
                if (learnerAps < programme.getMinimumAps()) {
                    continue;
                }

                // 2. Check subject requirements
                boolean meetsRequirements = true;

                for (ProgrammeSubjectRequirement requirement : programme.getSubjectRequirements()) {

                    boolean passed = learnerSubjects.stream()
                            .anyMatch(subject ->
                                    subject.getSubjectName().equalsIgnoreCase(requirement.getSubjectName())
                                            &&
                                            subject.getPercentage() >= requirement.getMinimumPercentage());

                    if (!passed) {
                        meetsRequirements = false;
                        break;
                    }
                }

                // Add programme if all requirements are satisfied
                if (meetsRequirements) {
                    qualifiedProgrammes.add(Helper.mapProgrammeToDTO(programme));
                }


            }


        int totalMatches = qualifiedProgrammes.size();


        if (qualifiedProgrammes.isEmpty()) {

            qualifiedProgrammes = programmes.stream()
                    .map(Helper::mapProgrammeToDTO)
                    .toList();

            // recommendation.setCourseName("Available Programmes");
        }

        CourseRecommendationDTO recommendation = new CourseRecommendationDTO();

        if (totalMatches == 0) {
            recommendation.setCourseName("Available Courses");
        } else {
            recommendation.setCourseName("Recommended Courses");
        }

        recommendation.setProgrammes(qualifiedProgrammes);

        recommendation.setUniversities(
                qualifiedProgrammes.stream()
                        .map(UniversityProgrammeDTO::getInstitutionName)
                        .distinct()
                        .toList());

        CareerAnalysisResponseDTO response = new CareerAnalysisResponseDTO();

        response.setAps(learnerAps);
        response.setTotalMatches(totalMatches);
        response.setRecommendations(List.of(recommendation));

        return Response.success(response, "Analysis completed successfully.");


    }
}
