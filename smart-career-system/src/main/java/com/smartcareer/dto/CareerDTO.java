package com.smartcareer.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CareerDTO {

  private  Long id;

  @NotBlank(message = "Career name is required")
  private String careerName;

  @NotBlank(message = "Description of thr career is required")
  @Size(max = 3000, message = "Description must have less than 3000 characters")
  private String description;

  @NotBlank(message = "Responsibilities related to the career are required")
  private String responsibilities;

  @NotBlank(message =  "Skills are required")
  private String requiredSkills;

  private String industries;

  @NotBlank(message = "Average salary is required")
  private String averageSalary;

  @NotBlank(message = "Study paths of the career are required")
  private String studyPath;

  //To link careers with university programmes
  private List<Long> programmeIds = new ArrayList<>();


}
