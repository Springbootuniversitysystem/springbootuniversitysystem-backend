package com.smartcareer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "university_programmes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UniversityProgramme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String institutionName;

    @Column(nullable = false)
    private String faculty;

    @Column(nullable = false)
    private String programmeName;

    @Column(nullable = false)
    private Integer minimumAps;

    private String description;

    private LocalDate applicationDeadline;
}