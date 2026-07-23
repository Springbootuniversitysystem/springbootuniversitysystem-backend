package com.smartcareer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CareerAnalysisResponseDTO {

    private Integer aps;

    private Integer totalMatches;

    private List<CourseRecommendationDTO> recommendations;
}
