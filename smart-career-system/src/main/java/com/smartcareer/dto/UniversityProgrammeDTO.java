package com.smartcareer.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UniversityProgrammeDTO {

    private Long id;

    @NotBlank(message = "Institution name is required")
    private String institutionName;

    @NotBlank(message = "Faculty name is required")
    private String faculty;

    @NotBlank(message = "Programme name is required")
    private String programmeName;

    @NotBlank(message = "Minimum APS Score is required")
    private Integer minimumAps;

    private List<ProgrammeSubjectRequirementDTO> subjectRequirements;

    private String description;

    private LocalDate applicationDeadline;
}
