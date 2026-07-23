package com.smartcareer.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectMarkDTO {

        @NotBlank(message = "Subject is required")
        private String subjectName;

        @NotBlank(message = "percentage is required")
        private Integer percentage;
}
