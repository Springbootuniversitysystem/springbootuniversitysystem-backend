package com.smartcareer.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlatformStatsDTO {
    private int totalProvinces;
    private long totalProgrammes;
    private long totalStudents;
}