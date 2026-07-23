package com.smartcareer.service;

import com.smartcareer.dto.*;
import com.smartcareer.entity.Career;
import com.smartcareer.entity.Learner;
import com.smartcareer.entity.ProgrammeSubjectRequirement;
import com.smartcareer.entity.UniversityProgramme;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

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
        learnerDTO.setGrade(request.getGrade());
        learnerDTO.setSchoolName(request.getSchoolName());

        String[] names = request.getFullName().trim().split("\\s+", 2);

        learnerDTO.setFirstName(names[0]);

        if (names.length > 1) {
            learnerDTO.setLastName(names[1]);
        } else {
            learnerDTO.setLastName("");
        }



        return learnerDTO;
    }
    //********************************* for career*******************************************
    public static void mapCareerFromDTO(Career career, CareerDTO dto) {

        career.setCareerName(dto.getCareerName());
        career.setDescription(dto.getDescription());
        career.setResponsibilities(dto.getResponsibilities());
        career.setRequiredSkills(dto.getRequiredSkills());
        career.setIndustries(dto.getIndustries());
        career.setAverageSalary(dto.getAverageSalary());
        career.setStudyPath(dto.getStudyPath());
    }

    public static CareerDTO mapCareerToDTO(Career career) {

        CareerDTO dto = new CareerDTO();

        dto.setId(career.getId());
        dto.setCareerName(career.getCareerName());
        dto.setDescription(career.getDescription());
        dto.setResponsibilities(career.getResponsibilities());
        dto.setRequiredSkills(career.getRequiredSkills());
        dto.setIndustries(career.getIndustries());
        dto.setAverageSalary(career.getAverageSalary());
        dto.setStudyPath(career.getStudyPath());

        return dto;
    }

    public static  void updateCareer(Career career, CareerDTO careerDTO)
    {
        if(career.getId() != null) {
            career.setId(career.getId());
        }
       if(careerDTO.getCareerName() != null) {
           career.setCareerName(careerDTO.getCareerName());
       }
       if(careerDTO.getDescription() != null) {
           career.setDescription(careerDTO.getDescription());
       }
       if(careerDTO.getResponsibilities() != null) {
           career.setResponsibilities(careerDTO.getResponsibilities());
       }
       if(careerDTO.getRequiredSkills() != null){
           career.setRequiredSkills(careerDTO.getRequiredSkills());
       }
       if(careerDTO.getIndustries() != null){
           career.setIndustries(careerDTO.getIndustries());
       }
       if(careerDTO.getAverageSalary() != null) {
           career.setAverageSalary(careerDTO.getAverageSalary());
       }
       if( careerDTO.getStudyPath() != null) {
           career.setStudyPath(careerDTO.getStudyPath());
       }

    }

    //***************For UniversityProgramme***********************************
    public static UniversityProgrammeDTO mapProgrammeToDTO(UniversityProgramme programme) {

        UniversityProgrammeDTO dto = new UniversityProgrammeDTO();

        dto.setId(programme.getId());
        dto.setInstitutionName(programme.getInstitutionName());
        dto.setFaculty(programme.getFaculty());
        dto.setProgrammeName(programme.getProgrammeName());
        dto.setMinimumAps(programme.getMinimumAps());
        dto.setDescription(programme.getDescription());
        dto.setApplicationDeadline(programme.getApplicationDeadline());

        dto.setSubjectRequirements(
                programme.getSubjectRequirements()
                        .stream()
                        .map(Helper::mapProgrammeSubjectRequirementToDTO)
                        .toList()
        );

        return dto;
    }

    public static void mapProgrammeFromDTO(UniversityProgramme programme, UniversityProgrammeDTO dto) {

        programme.setInstitutionName(dto.getInstitutionName());
        programme.setFaculty(dto.getFaculty());
        programme.setProgrammeName(dto.getProgrammeName());
        programme.setMinimumAps(dto.getMinimumAps());
        programme.setDescription(dto.getDescription());
        programme.setApplicationDeadline(dto.getApplicationDeadline());

        List<ProgrammeSubjectRequirement> requirements =
                dto.getSubjectRequirements()
                        .stream()
                        .map(Helper::mapProgrammeSubjectRequirementFromDTO)
                        .toList();

        requirements.forEach(r -> r.setProgramme(programme));

        programme.setSubjectRequirements(requirements);
    }

    public static void updateProgramme(UniversityProgramme programme, UniversityProgrammeDTO dto) {

        if (dto.getInstitutionName() != null) {
            programme.setInstitutionName(dto.getInstitutionName());
        }

        if (dto.getFaculty() != null) {
            programme.setFaculty(dto.getFaculty());
        }

        if (dto.getProgrammeName() != null) {
            programme.setProgrammeName(dto.getProgrammeName());
        }

        if (dto.getMinimumAps() != null) {
            programme.setMinimumAps(dto.getMinimumAps());
        }

        if (dto.getDescription() != null) {
            programme.setDescription(dto.getDescription());
        }

        if (dto.getApplicationDeadline() != null) {
            programme.setApplicationDeadline(dto.getApplicationDeadline());
        }

        if (dto.getSubjectRequirements() != null) {

            List<ProgrammeSubjectRequirement> requirements =
                    dto.getSubjectRequirements()
                            .stream()
                            .map(Helper::mapProgrammeSubjectRequirementFromDTO)
                            .toList();

            requirements.forEach(r -> r.setProgramme(programme));

            programme.setSubjectRequirements(new ArrayList<>(requirements));
        }


    }


    //********************************For ProgrammeSubjectRequired****************************

    public static ProgrammeSubjectRequirementDTO mapProgrammeSubjectRequirementToDTO(
            ProgrammeSubjectRequirement requirement) {

        ProgrammeSubjectRequirementDTO dto = new ProgrammeSubjectRequirementDTO();

        dto.setId(requirement.getId());
        dto.setSubjectName(requirement.getSubjectName());
        dto.setMinimumPercentage(requirement.getMinimumPercentage());

        return dto;
    }

    public static ProgrammeSubjectRequirement mapProgrammeSubjectRequirementFromDTO(
            ProgrammeSubjectRequirementDTO dto) {

        ProgrammeSubjectRequirement requirement = new ProgrammeSubjectRequirement();

        requirement.setId(dto.getId());
        requirement.setSubjectName(dto.getSubjectName());
        requirement.setMinimumPercentage(dto.getMinimumPercentage());

        return requirement;
    }
}
