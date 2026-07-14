package com.smartcareer.controller;


import com.smartcareer.dto.LearnerDTO;
import com.smartcareer.response.Response;
import com.smartcareer.service.LearnerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/learners")
@Validated
public class LearnerController {
    private final LearnerService learnerService;

    public LearnerController(LearnerService learnerService) {
        this.learnerService = learnerService;
    }

    //POST /api/v1/learners
    @PostMapping
    public ResponseEntity<Response<LearnerDTO>> createLearner(@Valid @RequestBody LearnerDTO learnerDTO)
    {
        Response<LearnerDTO> learnerDTOResponse = learnerService.createLearner(learnerDTO);
        return ResponseEntity.ok(learnerDTOResponse);
    }

    //GET /api/v1/learners
    @GetMapping
    public ResponseEntity<Response<List<LearnerDTO>>>  getLearners()
    {
        Response<List<LearnerDTO>> learners = learnerService.getAllLearners();
        return ResponseEntity.ok(learners);
    }

    //GET /api/v1/learners/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Response<LearnerDTO>> getLearnerById(@PathVariable Long id)
    {
        Response<LearnerDTO> learnerDTOResponse= learnerService.getLearnerById(id);
        return ResponseEntity.ok(learnerDTOResponse);
    }

    //PUT /api/v1/learners/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Response<LearnerDTO>> updateLearner(@PathVariable Long id,@RequestBody LearnerDTO learnerDTO)
    {
        Response<LearnerDTO> response = learnerService.updateLearner(id, learnerDTO);
        return  ResponseEntity.ok(response);
    }

    //DELETE /api/v1/learners/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Void>> deleteLearner(@PathVariable Long id)
    {
        Response<Void> response = learnerService.deleteLearner(id);
        return ResponseEntity.ok(response);
    }

    //GET /api/v1/learners/email/{email}
    @GetMapping("/email/{email}")
    public  ResponseEntity<Response<LearnerDTO>> getLearnerByEmail(@PathVariable String email)
    {
        Response<LearnerDTO> response = learnerService.findByEmail(email);
        return ResponseEntity.ok(response);
    }

    //GET /api/v1/learners/student-id/{learnerId}
    @GetMapping("/student-id/{learnerId}")
    public  ResponseEntity<Response<LearnerDTO>> getLearnerByLearnerId(@PathVariable String learnerId)
    {
        Response<LearnerDTO> response = learnerService.findByLearnerId(learnerId);
        return ResponseEntity.ok(response);
    }


}



