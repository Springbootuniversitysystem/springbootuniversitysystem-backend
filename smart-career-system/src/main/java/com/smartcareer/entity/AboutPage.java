package com.smartcareer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name= "about_page")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AboutPage {

    @Id
    private Long id;

    private String heroTitle;

    @Column(columnDefinition="TEXT")
    private String heroSubtitle;

    private String missionHeading;

    @Column(columnDefinition="TEXT")
    private String missionBody1;

    @Column(columnDefinition="TEXT")
    private String missionBody2;

    private String tagYear;

    private String tagText;

    private String teamTitle;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "about_page_id")
    @OrderBy("displayOrder ASC")
    private List<AboutMetric> metrics;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "about_page_id")
    @OrderBy("displayOrder ASC")
    private List<TeamMember> teamMembers;


}
