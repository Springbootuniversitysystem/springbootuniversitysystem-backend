package com.smartcareer.service;

import com.smartcareer.dto.LearnerDTO;
import com.smartcareer.response.Response;

import java.util.List;

public interface LearnerService {

   Response<LearnerDTO> createLearner(LearnerDTO learnerDTO);
   Response<LearnerDTO>  getLearnerById(Long id);
   Response<List<LearnerDTO>> getAllLearners();
   Response<LearnerDTO> updateLearner(Long id, LearnerDTO learnerDTO);
   Response<Void> deleteLearner(Long id);
   Response<LearnerDTO> findByEmail(String email);
   Response<LearnerDTO> findByStudentId(String studentId);
}
