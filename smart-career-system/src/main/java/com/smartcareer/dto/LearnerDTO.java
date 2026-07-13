package com.smartcareer.dto;

import com.smartcareer.entity.Gender;
import com.smartcareer.entity.Grade;
import jakarta.validation.constraints.Size;
import lombok.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LearnerDTO {

    private  Long id;

    @NotBlank(message = "ID number is required")
    @Size(min = 13, max = 13, message =  "The ID number must be 13 digits long")
    private  String studentId;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    @NotNull(message = "Date of birth is required")
    private LocalDate dateOfBirth;

    @NotNull(message = "Gender is required")
    private Gender gender;

    @NotNull(message = "Grade is required")
    private Grade grade;

    @NotBlank(message = "Province is required")
    private String province;

    @NotBlank(message = "School name is required")
    private String schoolName;

    @NotBlank(message = "Career goal is required")
    private String careerGoal;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
