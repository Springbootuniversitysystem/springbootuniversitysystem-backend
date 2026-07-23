package com.smartcareer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(
            mappedBy = "programme",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ProgrammeSubjectRequirement> subjectRequirements = new ArrayList<>();

    private String description;

    private LocalDate applicationDeadline;
}