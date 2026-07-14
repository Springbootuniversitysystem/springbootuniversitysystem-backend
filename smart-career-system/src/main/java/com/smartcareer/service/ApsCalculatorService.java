package com.smartcareer.service;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ApsCalculatorService {

    /**
     * Converts a single subject percentage into an APS point based on standard SA matric weightings.
     */
    public int convertPercentageToApsPoint(int percentage) {
        if (percentage >= 80 && percentage <= 100) return 7;
        if (percentage >= 70 && percentage <= 79) return 6;
        if (percentage >= 60 && percentage <= 69) return 5;
        if (percentage >= 50 && percentage <= 59) return 4;
        if (percentage >= 40 && percentage <= 49) return 3;
        if (percentage >= 30 && percentage <= 39) return 2;
        if (percentage >= 0 && percentage <= 29) return 1;

        throw new IllegalArgumentException("Invalid percentage: " + percentage);
    }

    /**
     * Takes a list of percentages (e.g., from the Learner's top 6 subjects) and calculates the total APS.
     */
    public int calculateTotalAps(List<Integer> subjectPercentages) {
        int totalAps = 0;

        for (Integer percentage : subjectPercentages) {
            totalAps += convertPercentageToApsPoint(percentage);
        }

        return totalAps;
    }
}