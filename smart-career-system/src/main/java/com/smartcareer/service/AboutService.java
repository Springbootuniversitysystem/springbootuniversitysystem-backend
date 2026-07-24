package com.smartcareer.service;

import com.smartcareer.dto.AboutPageDTO;
import com.smartcareer.dto.PlatformStatsDTO;
import com.smartcareer.response.Response;

public interface AboutService {

    Response<AboutPageDTO> getAboutPage();

    Response<AboutPageDTO> updatePage(AboutPageDTO dto);

    PlatformStatsDTO getLivePlatformStats();
}
