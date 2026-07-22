package com.smartcareer.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TeamMemberDTO {

    @NotBlank(message = "Name is required")
    private String fullName;

    @NotBlank(message = "Role is required")
    private String role;

    @NotBlank(message = "Description is required")
    private String description;
}