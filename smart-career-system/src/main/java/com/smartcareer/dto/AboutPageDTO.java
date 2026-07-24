package com.smartcareer.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AboutPageDTO {

        private Long id;

        @NotBlank
        private String heroTitle;

        @NotBlank
        private String heroSubtitle;

        @NotBlank
        private String missionHeading;

        private String missionBody1;

        private String missionBody2;

        private String tagYear;

        private String tagText;

        private String teamTitle;

        private List<MetricDTO> metrics;

        private List<TeamMemberDTO> teamMembers;

        // Live statistics
        private PlatformStatsDTO platformStats;


}
