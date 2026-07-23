package com.smartcareer.controller;

import com.smartcareer.dto.CareerAnalysisResponseDTO;
import com.smartcareer.dto.SubjectMarkDTO;
import com.smartcareer.dto.UniversityProgrammeDTO;
import com.smartcareer.response.Response;
import com.smartcareer.service.FilterProgrammesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/v1/programmes")
@RequiredArgsConstructor
public class FilterProgrammeController {

    private final FilterProgrammesService filterProgrammesService;

    @PostMapping("/qualified")
    public Response<CareerAnalysisResponseDTO> getQualifiedProgrammes(
            @RequestBody List<SubjectMarkDTO> learnerSubjects) {

        return filterProgrammesService.getQualifiedProgrammes(learnerSubjects);
    }

}
