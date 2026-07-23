package com.smartcareer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "saved_programmes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaveCourse {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "learner_id")
        private Learner learner;

        @ManyToOne
        @JoinColumn(name = "programme_id")
        private UniversityProgramme programme;

        private LocalDateTime savedAt;

}
