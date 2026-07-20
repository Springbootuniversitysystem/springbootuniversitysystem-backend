package com.smartcareer.controller;

import com.smartcareer.dto.CareerDTO;
import com.smartcareer.response.Response;
import com.smartcareer.response.ResponsePage;
import com.smartcareer.service.CareerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/careers")
@RequiredArgsConstructor
@Validated
public class CareerController {

    private final CareerService careerService;

    // POST /api/v1/careers
    @PostMapping
    public ResponseEntity<Response<CareerDTO>> createCareer(@Valid @RequestBody CareerDTO careerDTO) {

        Response<CareerDTO> response = careerService.createCareer(careerDTO);

        return ResponseEntity.ok(response);
    }

    // GET /api/v1/careers?page=0&size=10
    @GetMapping
    public ResponseEntity<Response<ResponsePage<CareerDTO>>> getAllCareers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);

        Response<ResponsePage<CareerDTO>> response = careerService.getAllCareers(pageable);

        return ResponseEntity.ok(response);
    }

    // GET /api/v1/careers/1
    @GetMapping("/{id}")
    public ResponseEntity<Response<CareerDTO>> getCareerById(
            @PathVariable Long id) {

        Response<CareerDTO> response = careerService.getCareerById(id);

        return ResponseEntity.ok(response);
    }

    // PUT /api/v1/careers/1
    @PutMapping("/{id}")
    public ResponseEntity<Response<CareerDTO>> updateCareer(@PathVariable Long id, @RequestBody CareerDTO careerDTO) {

        Response<CareerDTO> response = careerService.updateCareer(id, careerDTO);

        return ResponseEntity.ok(response);
    }

    // DELETE /api/v1/careers/1
    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Void>> deleteCareer(@PathVariable Long id) {

        Response<Void> response = careerService.deleteCareer(id);

        return ResponseEntity.ok(response);
    }

    // GET /api/v1/careers/search?careerName=Software&page=0&size=10
    @GetMapping("/search")
    public ResponseEntity<Response<ResponsePage<CareerDTO>>> searchCareerByName(
            @RequestParam String careerName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);

        Response<ResponsePage<CareerDTO>> response =
                careerService.getCareerByName(careerName, pageable);

        return ResponseEntity.ok(response);
    }
}
