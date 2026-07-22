package com.smartcareer.service;

import com.smartcareer.dto.DashboardDTO;
import com.smartcareer.response.Response;
import org.springframework.security.core.Authentication;

public interface DashboardService {

    Response<DashboardDTO> getDashboard(Authentication authentication);
}
