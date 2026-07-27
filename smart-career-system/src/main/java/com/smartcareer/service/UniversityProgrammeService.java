package com.smartcareer.service;

import com.smartcareer.dto.CareerDTO;
import com.smartcareer.dto.UniversityProgrammeDTO;
import com.smartcareer.entity.UniversityProgramme;
import com.smartcareer.exception.UniversityProgrammeNotFoundEx;
import com.smartcareer.repository.UniversityProgrammeRepository;
import com.smartcareer.response.Response;
import com.smartcareer.response.ResponsePage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UniversityProgrammeService {

    private final UniversityProgrammeRepository repository;

    //Update Destiney's UniversityProgrammeService with UniversityProgrammeDTO

    public Response<ResponsePage<UniversityProgrammeDTO>> getEligibleProgrammes(Integer userAps, Pageable pageable)
    {

        Page<UniversityProgramme> programmes = repository.findByMinimumApsLessThanEqual(userAps, pageable);

        Page<UniversityProgrammeDTO> programmeDTOs = programmes.map(Helper::mapProgrammeToDTO);

        ResponsePage<UniversityProgrammeDTO> responsePage = ResponsePage.createPage(programmeDTOs);

        return Response.success(responsePage, "Eligible programmes retrieved successfully");
    }

    public Response<UniversityProgrammeDTO> saveProgramme(UniversityProgrammeDTO programmeDTO) {

        UniversityProgramme programme = new UniversityProgramme();
        Helper.mapProgrammeFromDTO(programme, programmeDTO);

        UniversityProgramme savedProgramme = repository.save(programme);

        UniversityProgrammeDTO savedDTO = Helper.mapProgrammeToDTO(savedProgramme);

        return Response.success(savedDTO, "Programme created successfully");
    }
    public Response<UniversityProgrammeDTO> getProgrammeById(Long id) {

        UniversityProgramme programme = repository.findById(id).orElseThrow(() ->
                        new UniversityProgrammeNotFoundEx("Programme with id: " + id + " was not found"));

        UniversityProgrammeDTO programmeDTO = Helper.mapProgrammeToDTO(programme);

        return Response.success(programmeDTO, "Programme retrieved successfully");
    }
    public Response<ResponsePage<UniversityProgrammeDTO>> getAllProgrammes(Pageable pageable) {

        Page<UniversityProgramme> programmes = repository.findAll(pageable);

        Page<UniversityProgrammeDTO> programmeDTOs = programmes.map(Helper::mapProgrammeToDTO);

        ResponsePage<UniversityProgrammeDTO> responsePage = ResponsePage.createPage(programmeDTOs);

        return Response.success(responsePage, "Programmes were successfully retrieved"
        );
    }

    public Response<UniversityProgrammeDTO> updateProgramme(Long id, UniversityProgrammeDTO dto) {

        Optional<UniversityProgramme> programme = repository.findById(id);

        UniversityProgramme foundProgramme = programme.orElseThrow(() ->
                new UniversityProgrammeNotFoundEx("Programme with id: " + id + " is not found"));

        Helper.updateProgramme(foundProgramme, dto);

        UniversityProgramme updatedProgramme = repository.save(foundProgramme);

        UniversityProgrammeDTO updatedProgrammeDTO = Helper.mapProgrammeToDTO(updatedProgramme);

        return Response.success(updatedProgrammeDTO, "Programme was successfully updated");
    }

    public Response<Void> deleteProgramme(Long id) {

        Optional<UniversityProgramme> programme = repository.findById(id);

        UniversityProgramme foundProgramme = programme.orElseThrow(() ->
                new UniversityProgrammeNotFoundEx("Programme with id: " + id + " is not found"));

        repository.delete(foundProgramme);

        return Response.success(null, "Programme was successfully deleted");
    }
    public Response<UniversityProgrammeDTO> createProgramme(UniversityProgrammeDTO dto) {

        UniversityProgramme programme = new UniversityProgramme();

        Helper.mapProgrammeFromDTO(programme, dto);

        UniversityProgramme savedProgramme = repository.save(programme);

        UniversityProgrammeDTO savedDTO = Helper.mapProgrammeToDTO(savedProgramme);

        return Response.success(savedDTO, "Programme successfully created");
    }

    //Get careers from course
    public Response<List<CareerDTO>> getCareersByProgramme(Long programmeId) {

        UniversityProgramme programme = repository.findById(programmeId)
                .orElseThrow(() ->
                        new RuntimeException("Programme not found"));

        List<CareerDTO> careers = programme.getCareers()
                .stream()
                .map(Helper::mapCareerToDTO)
                .toList();

        return Response.success(careers,
                "Careers retrieved successfully.");
    }
}