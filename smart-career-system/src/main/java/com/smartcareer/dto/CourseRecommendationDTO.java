package com.smartcareer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseRecommendationDTO {

    private String courseName;

    private List<UniversityProgrammeDTO> programmes;

    private List<String> universities;
}
