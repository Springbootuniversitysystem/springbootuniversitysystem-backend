package com.smartcareer.controller;

import com.smartcareer.dto.PlatformStatsDTO;
import com.smartcareer.response.Response;
import com.smartcareer.service.AboutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/about")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AboutController {

    private final AboutService aboutService;

    @GetMapping("/stats")
    public ResponseEntity<Response<PlatformStatsDTO>> getStats() {
        PlatformStatsDTO stats = aboutService.getLivePlatformStats();
        return ResponseEntity.ok(Response.success(stats, "Real-time platform statistics loaded."));
    }
}