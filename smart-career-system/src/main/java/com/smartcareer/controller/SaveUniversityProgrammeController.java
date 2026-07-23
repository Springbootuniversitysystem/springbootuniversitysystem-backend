package com.smartcareer.controller;

import com.smartcareer.dto.UniversityProgrammeDTO;
import com.smartcareer.response.Response;
import com.smartcareer.service.SaveCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/saved-programmes")
@RequiredArgsConstructor
public class SaveUniversityProgrammeController {

    private final SaveCourseService saveCourseService;

    @PostMapping("/{programmeId}")
    public Response<Void> saveProgramme(@PathVariable Long programmeId,
                                        Authentication authentication) {

        return saveCourseService.saveProgramme(programmeId, authentication);
    }

    @GetMapping
    public Response<List<UniversityProgrammeDTO>> getSavedProgrammes(
            Authentication authentication) {

        return saveCourseService.getSavedProgrammes(authentication);
    }

    @DeleteMapping("/{programmeId}")
    public Response<Void> removeProgramme(@PathVariable Long programmeId,
                                          Authentication authentication) {

        return saveCourseService.removeProgramme(programmeId, authentication);
    }
}
