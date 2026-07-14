package com.smartcareer.controller;

import com.smartcareer.entity.ProgrammeApplication;
import com.smartcareer.response.Response;
import com.smartcareer.service.ProgrammeApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/applications")
@RequiredArgsConstructor
public class ProgrammeApplicationController {

    private final ProgrammeApplicationService applicationService;

    // Endpoint to save a programme to a learner's tracker
    @PostMapping("/save")
    public ResponseEntity<Response<ProgrammeApplication>> saveApplication(
            @RequestParam Long learnerId,
            @RequestParam Long programmeId) {

        ProgrammeApplication application = applicationService.saveProgrammeForLearner(learnerId, programmeId);

        // FIX: Using Emmanuel's static success method. (Data first, Message second)
        Response<ProgrammeApplication> response = Response.success(application, "Programme successfully saved to tracker");

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Endpoint to view all saved programmes for a specific learner
    @GetMapping("/learner/{learnerId}")
    public ResponseEntity<Response<List<ProgrammeApplication>>> getLearnerApplications(@PathVariable Long learnerId) {

        List<ProgrammeApplication> applications = applicationService.getLearnerApplications(learnerId);

        // FIX: Using Emmanuel's static success method for the List. (Data first, Message second)
        Response<List<ProgrammeApplication>> response = Response.success(applications, "Applications retrieved successfully");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}