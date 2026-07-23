package com.smartcareer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardDTO {

        private long guidanceSessions;
        private long savedCourses;
        private int cvCompletion;

}
