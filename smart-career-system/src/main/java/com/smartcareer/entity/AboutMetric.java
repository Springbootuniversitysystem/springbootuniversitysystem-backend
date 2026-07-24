package com.smartcareer.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "about_metrics")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AboutMetric {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String metricKey;

    private String title;

    private String description;

    private String icon;

    private Integer displayOrder;
}
