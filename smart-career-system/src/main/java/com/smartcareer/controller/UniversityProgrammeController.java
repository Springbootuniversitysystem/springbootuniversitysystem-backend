package com.smartcareer.controller;

import com.smartcareer.dto.UniversityProgrammeDTO;
import com.smartcareer.response.Response;
import com.smartcareer.response.ResponsePage;
import com.smartcareer.service.UniversityProgrammeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/programmes")
@RequiredArgsConstructor
public class UniversityProgrammeController {


    private final UniversityProgrammeService programmeService;

    @PostMapping
    public Response<UniversityProgrammeDTO> createProgramme(@RequestBody UniversityProgrammeDTO dto) {

        return programmeService.createProgramme(dto);
    }

    @GetMapping("/{id}")
    public Response<UniversityProgrammeDTO> getProgrammeById(
            @PathVariable Long id) {

        return programmeService.getProgrammeById(id);
    }

    @GetMapping
    public Response<ResponsePage<UniversityProgrammeDTO>> getAllProgrammes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);

        return programmeService.getAllProgrammes(pageable);
    }

    @PutMapping("/{id}")
    public Response<UniversityProgrammeDTO> updateProgramme(
            @PathVariable Long id,
            @RequestBody UniversityProgrammeDTO dto) {

        return programmeService.updateProgramme(id, dto);
    }

    @DeleteMapping("/{id}")
    public Response<Void> deleteProgramme(
            @PathVariable Long id) {

        return programmeService.deleteProgramme(id);
    }

    @GetMapping("/eligible")
    public Response<ResponsePage<UniversityProgrammeDTO>> getEligibleProgrammes(
            @RequestParam Integer aps,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);

        return programmeService.getEligibleProgrammes(aps, pageable);
    }
}
