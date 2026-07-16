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
@CrossOrigin(origins = "*") // Allows React frontend to connect
public class ProgrammeApplicationController {

    private final ProgrammeApplicationService applicationService;

    // POST: Save an application
    @PostMapping("/save")
    public ResponseEntity<Response<ProgrammeApplication>> saveApplication(
            @RequestParam Long learnerId,
            @RequestParam Long programmeId) {
        ProgrammeApplication application = applicationService.saveProgrammeForLearner(learnerId, programmeId);
        Response<ProgrammeApplication> response = Response.success(application, "Programme successfully saved to tracker");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // GET: View all applications for a learner
    @GetMapping("/learner/{learnerId}")
    public ResponseEntity<Response<List<ProgrammeApplication>>> getLearnerApplications(@PathVariable Long learnerId) {
        List<ProgrammeApplication> applications = applicationService.getLearnerApplications(learnerId);
        Response<List<ProgrammeApplication>> response = Response.success(applications, "Applications retrieved successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // PUT: Update application status (e.g., SAVED to APPLIED)
    @PutMapping("/{id}/status")
    public ResponseEntity<Response<ProgrammeApplication>> updateApplicationStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        ProgrammeApplication updatedApplication = applicationService.updateApplicationStatus(id, status);
        Response<ProgrammeApplication> response = Response.success(updatedApplication, "Application status updated successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // DELETE: Withdraw/Remove an application
    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Void>> deleteApplication(@PathVariable Long id) {
        applicationService.deleteApplication(id);
        Response<Void> response = Response.success(null, "Application successfully withdrawn");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}