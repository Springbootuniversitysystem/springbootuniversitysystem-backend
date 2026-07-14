package com.smartcareer.service;

import com.smartcareer.dto.LearnerDTO;
import com.smartcareer.dto.RegisterRequestDTO;
import com.smartcareer.entity.Learner;

import java.time.LocalDateTime;

public class Helper {


    public static LearnerDTO mapLearnerToDTO(Learner learner) {

        LearnerDTO learnerDTO = new LearnerDTO();

        learnerDTO.setId(learner.getId());
        learnerDTO.setLearnerId(learner.getLearnerId());
        learnerDTO.setFirstName(learner.getFirstName());
        learnerDTO.setLastName(learner.getLastName());
        learnerDTO.setEmail(learner.getEmail());
        learnerDTO.setPhoneNumber(learner.getPhoneNumber());
        learnerDTO.setDateOfBirth(learner.getDateOfBirth());
        learnerDTO.setGender(learner.getGender());
        learnerDTO.setGrade(learner.getGrade());
        learnerDTO.setProvince(learner.getProvince());
        learnerDTO.setSchoolName(learner.getSchoolName());
        learnerDTO.setCareerGoal(learner.getCareerGoal());
        learnerDTO.setCreatedAt(learner.getCreatedAt());
        learnerDTO.setUpdatedAt(learner.getUpdatedAt());

        return learnerDTO;

    }
    public static void mapLearnerFromDTO(Learner learner, LearnerDTO learnerDTO) {

        learner.setLearnerId(learnerDTO.getLearnerId());
        learner.setFirstName(learnerDTO.getFirstName());
        learner.setLastName(learnerDTO.getLastName());
        learner.setEmail(learnerDTO.getEmail());
        learner.setPhoneNumber(learnerDTO.getPhoneNumber());
        learner.setDateOfBirth(learnerDTO.getDateOfBirth());
        learner.setGender(learnerDTO.getGender());
        learner.setGrade(learnerDTO.getGrade());
        learner.setProvince(learnerDTO.getProvince());
        learner.setSchoolName(learnerDTO.getSchoolName());
        learner.setCareerGoal(learnerDTO.getCareerGoal());


    }

    public static  void updateLearner(Learner learner, LearnerDTO learnerDTO)
    {

        if (learnerDTO.getLearnerId() != null) {
            learner.setLearnerId(learnerDTO.getLearnerId());
        }

        if (learnerDTO.getFirstName() != null) {
            learner.setFirstName(learnerDTO.getFirstName());
        }

        if (learnerDTO.getLastName() != null) {
            learner.setLastName(learnerDTO.getLastName());
        }

        if (learnerDTO.getEmail() != null) {
            learner.setEmail(learnerDTO.getEmail());
        }

        if (learnerDTO.getPhoneNumber() != null) {
            learner.setPhoneNumber(learnerDTO.getPhoneNumber());
        }

        if (learnerDTO.getDateOfBirth() != null) {
            learner.setDateOfBirth(learnerDTO.getDateOfBirth());
        }

        if (learnerDTO.getGender() != null) {
            learner.setGender(learnerDTO.getGender());
        }

        if (learnerDTO.getGrade() != null) {
            learner.setGrade(learnerDTO.getGrade());
        }

        if (learnerDTO.getProvince() != null) {
            learner.setProvince(learnerDTO.getProvince());
        }

        if (learnerDTO.getSchoolName() != null) {
            learner.setSchoolName(learnerDTO.getSchoolName());
        }

        if (learnerDTO.getCareerGoal() != null) {
            learner.setCareerGoal(learnerDTO.getCareerGoal());
        }

        learner.setUpdatedAt(LocalDateTime.now());

    }

    public static LearnerDTO mapRegisterRequestToLearnerDTO(RegisterRequestDTO request) {

        LearnerDTO learnerDTO = new LearnerDTO();

        learnerDTO.setEmail(request.getEmail());
        learnerDTO.setPhoneNumber(request.getPhoneNumber());

        return learnerDTO;
    }
}
