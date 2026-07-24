package com.smartcareer.dto;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MetricDTO {

    private Long id;

    private String metricKey;

    private String title;

    private String description;

    private String icon;

    private Integer displayOrder;
}
