package com.smartcareer.controller;

import com.smartcareer.dto.AboutPageDTO;
import com.smartcareer.dto.PlatformStatsDTO;
import com.smartcareer.response.Response;
import com.smartcareer.service.AboutService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/about")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AboutController {

    private final AboutService aboutService;

    /**
     * Retrieve the About Us page.
     * Accessible by everyone.
     *
     * GET /api/v1/about
     */
    @GetMapping
    public ResponseEntity<Response<AboutPageDTO>> getAboutPage() {

        return ResponseEntity.ok(
                aboutService.getAboutPage()
        );
    }

    /**
     * Update the About Us page.
     * Should only be accessible by ADMIN users.
     *
     * PUT /api/v1/about
     */
    @PutMapping
    public ResponseEntity<Response<AboutPageDTO>> updateAboutPage(
            @Valid @RequestBody AboutPageDTO aboutPageDTO) {

        return ResponseEntity.ok(
                aboutService.updatePage(aboutPageDTO)
        );
    }
}