package com.smartcareer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProgrammeSubjectRequirementDTO {

    private Long id;
    private String subjectName;
    private Integer minimumPercentage;
}
