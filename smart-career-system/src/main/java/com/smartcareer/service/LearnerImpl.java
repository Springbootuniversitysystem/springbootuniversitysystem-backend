package com.smartcareer.service;

import com.smartcareer.dto.LearnerDTO;
import com.smartcareer.entity.Learner;
import com.smartcareer.exception.LearnerNotFoundException;
import com.smartcareer.repository.LearnerRepository;
import com.smartcareer.response.Response;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LearnerImpl implements  LearnerService{

    public final LearnerRepository learnerRepository;

    public LearnerImpl(LearnerRepository learnerRepository) {
        this.learnerRepository = learnerRepository;
    }


    @Override
    public Response<LearnerDTO> createLearner(LearnerDTO learnerDTO) {

        // map LearnerDTO to the learner Entity
        Learner learner = new Learner();
        Helper.mapLearnerFromDTO(learner,learnerDTO);

        learner.setCreatedAt(LocalDateTime.now());
        learner.setUpdatedAt(LocalDateTime.now());

        Learner savedLearner = learnerRepository.save(learner);


        // Map Learner Entity back to LearnerDTO
        LearnerDTO savedLearnerDTO = Helper.mapLearnerToDTO(savedLearner);



        return Response.success(savedLearnerDTO, "Learner created successfully.");
    }

    @Override
    public Response<LearnerDTO> getLearnerById(Long id) {

        Optional<Learner> learner = learnerRepository.findById(id);

        Learner foundLearner = learner.orElseThrow(()->
                                       new LearnerNotFoundException("Learner with id:"+id+" is not found"));

        LearnerDTO learnerDTO= Helper.mapLearnerToDTO(foundLearner);

        return Response.success(learnerDTO,"Learner is successfully retrieved");
    }

    @Override
    public Response<List<LearnerDTO>> getAllLearners() {

        List<Learner> learners = learnerRepository.findAll();

        List<LearnerDTO> learnerDTOS = learners.stream().map(Helper::mapLearnerToDTO).toList();

        return  Response.success(learnerDTOS, "Learners were successfully retrieved");
    }

    @Override
    public Response<LearnerDTO> updateLearner(Long id, LearnerDTO learnerDTO) {

        Optional<Learner> learner = learnerRepository.findById(id);
        Learner foundLearner = learner.orElseThrow(()->
                                        new LearnerNotFoundException("Learner with id:"+id+" is not found"));

        // Update the existing learner
        Helper.updateLearner(foundLearner, learnerDTO);


        // Save changes
        Learner updatedLearner = learnerRepository.save(foundLearner);

        // Convert to DTO
        LearnerDTO updatedLearnerDTO = Helper.mapLearnerToDTO(updatedLearner);

        return Response.success(updatedLearnerDTO, "Learner updated successfully.");

    }

    @Override
    public Response<Void> deleteLearner(Long id) {

        Optional<Learner> learner = learnerRepository.findById(id);

        Learner foundLearner = learner.orElseThrow(() ->
                        new LearnerNotFoundException("Learner with id: " + id + " was not found."));

        learnerRepository.delete(foundLearner);

        return Response.success(null, "Learner deleted successfully.");
    }

    @Override
    public Response<LearnerDTO> findByEmail(String email) {

        Learner learner = learnerRepository.findByEmail(email).orElseThrow(() ->
                        new LearnerNotFoundException("Learner with email '" + email + "' was not found."));

        LearnerDTO learnerDTO = Helper.mapLearnerToDTO(learner);

        return Response.success(learnerDTO, "Learner retrieved successfully.");
    }

    @Override
    public Response<LearnerDTO> findByLearnerId(String learnerId) {
        Learner learner = learnerRepository.findByLearnerId(learnerId)
                .orElseThrow(() ->
                        new LearnerNotFoundException("Learner with student ID '" + learnerId + "' was not found."));

        LearnerDTO learnerDTO = Helper.mapLearnerToDTO(learner);

        return Response.success(learnerDTO, "Learner retrieved successfully.");
    }
}
