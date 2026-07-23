package com.smartcareer.service;

import com.smartcareer.dto.CareerAnalysisResponseDTO;
import com.smartcareer.dto.SubjectMarkDTO;
import com.smartcareer.response.Response;

import java.util.List;

public interface FilterProgrammesService {

  Response<CareerAnalysisResponseDTO> getQualifiedProgrammes(List<SubjectMarkDTO> learnerSubjects);

}
