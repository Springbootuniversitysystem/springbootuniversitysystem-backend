package com.smartcareer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UniversityProgrammeDTO {

    private Long id;

    private String institutionName;

    private String faculty;

    private String programmeName;

    private Integer minimumAps;

    private String description;

    private LocalDate applicationDeadline;
}
