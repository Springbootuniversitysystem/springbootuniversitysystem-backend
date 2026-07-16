package com.smartcareer.controller;

import com.smartcareer.entity.UniversityProgramme;
import com.smartcareer.response.Response;
import com.smartcareer.service.UniversityProgrammeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/programmes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // Critical for the React frontend to connect later!
public class UniversityProgrammeController {

    private final UniversityProgrammeService programmeService;

    // GET: Retrieve all university programmes
    @GetMapping
    public ResponseEntity<Response<List<UniversityProgramme>>> getAllProgrammes() {
        List<UniversityProgramme> programmes = programmeService.getAllProgrammes();
        Response<List<UniversityProgramme>> response = Response.success(programmes, "Programmes retrieved successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // POST: Add a new programme to the database
    @PostMapping
    public ResponseEntity<Response<UniversityProgramme>> addProgramme(@RequestBody UniversityProgramme programme) {
        UniversityProgramme savedProgramme = programmeService.saveProgramme(programme);
        Response<UniversityProgramme> response = Response.success(savedProgramme, "Programme added successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // PUT: Update an existing programme
    @PutMapping("/{id}")
    public ResponseEntity<Response<UniversityProgramme>> updateProgramme(
            @PathVariable Long id,
            @RequestBody UniversityProgramme programme) {
        UniversityProgramme updatedProgramme = programmeService.updateProgramme(id, programme);
        Response<UniversityProgramme> response = Response.success(updatedProgramme, "Programme updated successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // DELETE: Remove a programme from the database
    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Void>> deleteProgramme(@PathVariable Long id) {
        programmeService.deleteProgramme(id);
        Response<Void> response = Response.success(null, "Programme deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}