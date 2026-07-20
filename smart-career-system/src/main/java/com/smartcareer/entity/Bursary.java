package com.smartcareer.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "bursaries")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bursary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Bursary provider name cannot be blank")
    @Size(max = 150)
    @Column(nullable = false, length = 150)
    private String providerName;

    @NotBlank(message = "Bursary title cannot be blank")
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String title;

    @NotNull(message = "Minimum APS requirement must be provided")
    @Min(0) @Max(50)
    @Column(nullable = false)
    private Integer requiredAps;

    @NotBlank(message = "Target field of study is required")
    @Column(nullable = false)
    private String fieldOfStudy;

    @Column(columnDefinition = "TEXT")
    private String coverageDetails; // Details like Tuition, Accommodation, Allowance

    @NotNull(message = "Application deadline is required")
    @Future(message = "Deadline must be a future date")
    @Column(nullable = false)
    private LocalDate deadline;
}