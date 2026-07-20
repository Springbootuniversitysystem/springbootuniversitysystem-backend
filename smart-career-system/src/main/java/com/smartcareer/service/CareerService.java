package com.smartcareer.service;

import com.smartcareer.dto.CareerDTO;
import com.smartcareer.response.Response;
import com.smartcareer.response.ResponsePage;
import org.springframework.data.domain.Pageable;


public interface CareerService {

    Response<CareerDTO> createCareer(CareerDTO careerDTO);

    Response<CareerDTO> getCareerById(Long id);

    //Respond with a page
    Response<ResponsePage<CareerDTO>> getAllCareers(Pageable pageable);

    Response<CareerDTO> updateCareer(Long id, CareerDTO careerDTO);

    Response<Void> deleteCareer(Long id);

    Response<ResponsePage<CareerDTO>> getCareerByName(String careerName, Pageable  pageable);
}
