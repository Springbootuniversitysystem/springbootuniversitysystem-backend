package com.smartcareer.service;

import com.smartcareer.dto.UniversityProgrammeDTO;
import com.smartcareer.response.Response;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface SaveCourseService {

    Response<Void> saveProgramme(Long programmeId, Authentication authentication);
    Response<List<UniversityProgrammeDTO>> getSavedProgrammes(Authentication authentication);
    Response<Void> removeProgramme(Long programmeId, Authentication authentication);
}
