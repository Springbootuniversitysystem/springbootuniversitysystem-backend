package com.smartcareer.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeamMemberDTO {

    private Long id;

    private String fullName;

    private String position;

    private String biography;

    private String initials;

    private String imageUrl;

    private Integer displayOrder;


}
