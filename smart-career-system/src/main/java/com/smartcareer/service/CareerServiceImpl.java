package com.smartcareer.service;

import com.smartcareer.dto.CareerDTO;
import com.smartcareer.entity.Career;
import com.smartcareer.exception.CareerNotFoundException;
import com.smartcareer.repository.CareerRepository;
import com.smartcareer.response.Response;
import com.smartcareer.response.ResponsePage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CareerServiceImpl implements  CareerService{

    private  final CareerRepository careerRepository;

    @Override
    public Response<CareerDTO> createCareer(CareerDTO careerDTO) {

        if (careerRepository.existsByCareerName(careerDTO.getCareerName())) {
            throw new IllegalArgumentException("Career already exists");
        }

        Career career = new Career();
        Helper.mapCareerFromDTO(career, careerDTO);

        Career savedCareer = careerRepository.save(career);

        CareerDTO savedDTO= Helper.mapCareerToDTO(savedCareer);

        return Response.success(savedDTO, "Career is successfully created");
    }

    @Override
    public Response<CareerDTO> getCareerById(Long id) {

        Optional<Career> career = careerRepository.findById(id);
        Career foundCareer = career.orElseThrow(()->new CareerNotFoundException("Career with id: "+id+"is not found"));

        CareerDTO careerDTO = Helper.mapCareerToDTO(foundCareer);

        return Response.success(careerDTO, "Career is successfully retrieved");
    }

    @Override
    public Response<ResponsePage<CareerDTO>> getAllCareers(Pageable pageable) {

        Page<Career> careers = careerRepository.findAll(pageable);

        Page<CareerDTO> careerDTOS = careers.map(Helper::mapCareerToDTO);

        ResponsePage<CareerDTO> responsePage = ResponsePage.createPage(careerDTOS);

        return Response.success(responsePage, "Careers were successfully retrieved");
    }

    @Override
    public Response<CareerDTO> updateCareer(Long id, CareerDTO careerDTO) {

        Optional<Career> career = careerRepository.findById(id);
        Career foundCareer= career.orElseThrow(()->new CareerNotFoundException("Career with id: "+id+"is not found"));
        Helper.updateCareer(foundCareer,careerDTO);

        Career updatedCareer = careerRepository.save(foundCareer);

        CareerDTO updatedCareerDTO=  Helper.mapCareerToDTO(updatedCareer);

        return Response.success(updatedCareerDTO,"Career is successfully updated");
    }

    @Override
    public Response<Void> deleteCareer(Long id) {

        Optional<Career> career = careerRepository.findById(id);
        Career foundCareer= career.orElseThrow(()->new CareerNotFoundException("Career with id: "+id+"is not found"));

        careerRepository.delete(foundCareer);

        return Response.success(null, "Career is successfully deleted");
    }

    @Override
    public Response<ResponsePage<CareerDTO>> getCareerByName(String careerName, Pageable pageable) {

        Page<Career> careers = careerRepository.findByCareerNameContainingIgnoreCase(careerName,pageable);

        Page<CareerDTO> careerDTOS = careers.map(Helper::mapCareerToDTO);

        ResponsePage<CareerDTO> careerDTOResponsePage = ResponsePage.createPage(careerDTOS);

        return Response.success(careerDTOResponsePage, "Career was successfully retrieved");
    }
}
