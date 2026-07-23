package com.smartcareer.service;

import com.smartcareer.dto.UniversityProgrammeDTO;
import com.smartcareer.entity.Learner;
import com.smartcareer.entity.SaveCourse;
import com.smartcareer.entity.UniversityProgramme;
import com.smartcareer.entity.User;
import com.smartcareer.exception.LearnerNotFoundException;
import com.smartcareer.exception.UniversityProgrammeNotFoundEx;
import com.smartcareer.repository.LearnerRepository;
import com.smartcareer.repository.SavedCourseRepository;
import com.smartcareer.repository.UniversityProgrammeRepository;
import com.smartcareer.response.Response;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SaveCourseServiceImp  implements  SaveCourseService{

    private  final LearnerRepository learnerRepository;
    private  final UniversityProgrammeRepository programmeRepository;
    private  final SavedCourseRepository savedProgrammeRepository;

    @Transactional
    @Override
    public Response<Void> saveProgramme(Long programmeId, Authentication authentication) {

        User user = (User) authentication.getPrincipal();

        Learner learner = learnerRepository.findByEmail(user.getEmail()).orElseThrow(() ->
                        new LearnerNotFoundException("Learner not found"));

        UniversityProgramme programme = programmeRepository.findById(programmeId).orElseThrow(() ->
                        new UniversityProgrammeNotFoundEx("Programme not found"));

        if(savedProgrammeRepository.existsByLearnerAndProgramme(learner, programme)){
            throw new RuntimeException("Programme already saved");
        }

        SaveCourse saved = new SaveCourse();
        saved.setLearner(learner);
        saved.setProgramme(programme);
        saved.setSavedAt(LocalDateTime.now());

        savedProgrammeRepository.save(saved);

        return Response.success(null, "Programme saved");
    }

    @Override
    public Response<List<UniversityProgrammeDTO>> getSavedProgrammes(Authentication authentication) {

        User user = (User) authentication.getPrincipal();

        Learner learner = learnerRepository.findByEmail(user.getEmail()).orElseThrow(() ->
                new LearnerNotFoundException("Learner not found"));

        List<SaveCourse> savedCourses = savedProgrammeRepository.findByLearner(learner);

        List<UniversityProgrammeDTO> programmes = savedCourses.stream()
                .map(save -> Helper.mapProgrammeToDTO(save.getProgramme()))
                .toList();

        return Response.success(programmes, "Saved programmes retrieved successfully");
    }

    @Override
    public Response<Void> removeProgramme(Long programmeId, Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        Learner learner = learnerRepository.findByEmail(user.getEmail()).orElseThrow(() ->
                new LearnerNotFoundException("Learner not found"));

        UniversityProgramme programme = programmeRepository.findById(programmeId).orElseThrow(() ->
                new UniversityProgrammeNotFoundEx("Programme not found"));

        savedProgrammeRepository.deleteByLearnerAndProgramme(learner, programme);

        return Response.success(null, "Programme removed successfully");
    }
}
