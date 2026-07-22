package com.smartcareer.controller;

import com.smartcareer.dto.DashboardDTO;
import com.smartcareer.response.Response;
import com.smartcareer.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    public Response<DashboardDTO> getDashboard(Authentication authentication) {
        return dashboardService.getDashboard(authentication);
    }

}
