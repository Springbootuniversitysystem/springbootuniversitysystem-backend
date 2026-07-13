package com.smartcareer.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "learners")
@Getter               // getter methods
@Setter               // setter methods
@NoArgsConstructor    // default constructor
@AllArgsConstructor   // args constructor
public class Learner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private Gender gender;
    private Grade grade;
    private String province;
    private String schoolName;
    private String careerGoal;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
