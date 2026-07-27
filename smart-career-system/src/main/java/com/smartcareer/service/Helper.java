package com.smartcareer.service;

import com.smartcareer.dto.*;
import com.smartcareer.entity.*;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.UUID;

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
        dto.setProgrammeIds(
                career.getProgrammes().stream()
                        .map(UniversityProgramme::getId)
                        .toList()
        );

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
        dto.setCareers(
                programme.getCareers().stream()
                        .map(Helper::mapCareerToDTO)
                        .toList()
        );

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

   // *****************************UUID *****************************

    public static String generateToken() {
        return UUID.randomUUID().toString();
    }

    //****************************For About Page****************************

    public static AboutPageDTO mapAboutPageToDTO(AboutPage page)
    {

        if (page == null) {
            return null;
        }

        return AboutPageDTO.builder()
                .id(1L) // Always the single About page
                .heroTitle(page.getHeroTitle())
                .heroSubtitle(page.getHeroSubtitle())
                .missionHeading(page.getMissionHeading())
                .missionBody1(page.getMissionBody1())
                .missionBody2(page.getMissionBody2())
                .tagYear(page.getTagYear())
                .tagText(page.getTagText())
                .teamTitle(page.getTeamTitle())

                .metrics(
                        page.getMetrics()
                                .stream()
                                .map(Helper::mapMetricToDTO)
                                .toList()
                )

                .teamMembers(
                        page.getTeamMembers()
                                .stream()
                                .map(Helper::mapTeamMemberToDTO)
                                .toList()
                )

                .build();

    }

    public static void mapAboutPageFromDTO(AboutPage page, AboutPageDTO dto)
    {

        if (page == null || dto == null) {
            return;
        }

        page.setId(1L);

        page.setHeroTitle(dto.getHeroTitle());
        page.setHeroSubtitle(dto.getHeroSubtitle());

        page.setMissionHeading(dto.getMissionHeading());
        page.setMissionBody1(dto.getMissionBody1());
        page.setMissionBody2(dto.getMissionBody2());

        page.setTagYear(dto.getTagYear());
        page.setTagText(dto.getTagText());

        page.setTeamTitle(dto.getTeamTitle());

        // Map Metrics
        if (dto.getMetrics() != null) {
            page.setMetrics(
                    dto.getMetrics()
                            .stream()
                            .map(Helper::mapMetricFromDTO)
                            .toList()
            );
        }

        // Map Team Members
        if (dto.getTeamMembers() != null) {
            page.setTeamMembers(
                    dto.getTeamMembers()
                            .stream()
                            .map(Helper::mapTeamMemberFromDTO)
                            .toList()
            );
        }

    }

    public static AboutMetric mapMetricFromDTO(MetricDTO dto) {

        if (dto == null) {
            return null;
        }

        return AboutMetric.builder()
                .id(dto.getId())
                .metricKey(dto.getMetricKey())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .icon(dto.getIcon())
                .displayOrder(dto.getDisplayOrder())
                .build();
    }

    public static TeamMember mapTeamMemberFromDTO(TeamMemberDTO dto) {

        if (dto == null) {
            return null;
        }

        return TeamMember.builder()
                .id(dto.getId())
                .fullName(dto.getFullName())
                .position(dto.getPosition())
                .biography(dto.getBiography())
                .initials(dto.getInitials())
                .imageUrl(dto.getImageUrl())
                .displayOrder(dto.getDisplayOrder())
                .build();
    }

    public static MetricDTO mapMetricToDTO(AboutMetric metric) {

        if (metric == null) {
            return null;
        }

        return MetricDTO.builder()
                .id(metric.getId())
                .metricKey(metric.getMetricKey())
                .title(metric.getTitle())
                .description(metric.getDescription())
                .icon(metric.getIcon())
                .displayOrder(metric.getDisplayOrder())
                .build();
    }

    public static TeamMemberDTO mapTeamMemberToDTO(TeamMember member) {

        if (member == null) {
            return null;
        }

        return TeamMemberDTO.builder()
                .id(member.getId())
                .fullName(member.getFullName())
                .position(member.getPosition())
                .biography(member.getBiography())
                .initials(member.getInitials())
                .imageUrl(member.getImageUrl())
                .displayOrder(member.getDisplayOrder())
                .build();
    }

    //Random number generator

    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generateVerificationCode() {
        int code = 100000 + RANDOM.nextInt(900000);
        return String.valueOf(code);
    }


}
