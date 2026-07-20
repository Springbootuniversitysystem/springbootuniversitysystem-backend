package com.smartcareer.controller;

import com.smartcareer.entity.Bursary;
import com.smartcareer.response.Response;
import com.smartcareer.service.BursaryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bursaries")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class BursaryController {

    private final BursaryService bursaryService;

    @GetMapping
    public ResponseEntity<Response<Page<Bursary>>> getAllBursaries(Pageable pageable) {
        Page<Bursary> data = bursaryService.getAllBursaries(pageable);
        return ResponseEntity.ok(Response.success(data, "Bursary database records loaded."));
    }

    @GetMapping("/eligible")
    public ResponseEntity<Response<Page<Bursary>>> getEligibleBursaries(@RequestParam Integer aps, Pageable pageable) {
        Page<Bursary> data = bursaryService.getEligibleBursaries(aps, pageable);
        return ResponseEntity.ok(Response.success(data, "Eligible match items retrieved."));
    }

    @PostMapping
    public ResponseEntity<Response<Bursary>> createBursary(@Valid @RequestBody Bursary bursary) {
        Bursary saved = bursaryService.createBursary(bursary);
        return new ResponseEntity<>(Response.success(saved, "Bursary option created."), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<Bursary>> updateBursary(@PathVariable Long id, @Valid @RequestBody Bursary bursary) {
        Bursary updated = bursaryService.updateBursary(id, bursary);
        return ResponseEntity.ok(Response.success(updated, "Bursary structural update finalized."));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Void>> deleteBursary(@PathVariable Long id) {
        bursaryService.deleteBursary(id);
        return ResponseEntity.ok(Response.success(null, "Bursary reference dropped from ledger."));
    }
}