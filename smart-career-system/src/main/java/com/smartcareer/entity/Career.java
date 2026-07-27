package com.smartcareer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "careers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Career {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private String careerName;
    private String description;
    private String responsibilities;
    private String requiredSkills;
    private String industries;
    private String averageSalary;
    private String studyPath;

    @ManyToMany(mappedBy = "careers")
    private List<UniversityProgramme> programmes = new ArrayList<>();

}
