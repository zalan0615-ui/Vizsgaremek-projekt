package com.gymforge.service;

import com.gymforge.dto.request.UpdateGymInfoRequest;
import com.gymforge.dto.response.GymInfoResponse;
import com.gymforge.exception.ResourceNotFoundException;
import com.gymforge.model.GymInfo;
import com.gymforge.repository.GymInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//---------------------------------------------------------------------------------------------------------//
//---------------------------------------------------------------------------------------------------------//

@Service
@RequiredArgsConstructor
public class GymInfoService {

    private final GymInfoRepository gymInfoRepository;

    //-----------------------------------------------------------------------------
    // Lekérés (getgym_info eljárás)

    public List<GymInfoResponse> getAllGymInfo() {
        return gymInfoRepository.getAllGymInfo()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    //-----------------------------------------------------------------------------
    // Lekérés id alapján (getByIdgym_info eljárás)

    public GymInfoResponse getGymInfoById(Integer id) {
        return toResponse(findGymInfo(id));
    }

    //-----------------------------------------------------------------------------
    // Módosítás (updategym_info eljárás)

    @Transactional
    public GymInfoResponse updateGymInfo(Integer id, UpdateGymInfoRequest request) {
        findGymInfo(id); // ha nincs ilyen sor, itt jön a 404

        gymInfoRepository.updateGymInfo(
                id,
                request.getGymName(),
                request.getAddress(),
                request.getPhoneNumber(),
                request.getPremiumMachineNumber(),
                request.getEmail(),
                request.getOpeningHours(),
                request.getUsersNumber(),
                request.getTrainersNumber()
        );

        return toResponse(findGymInfo(id));
    }

    //-----------------------------------------------------------------------------
    // Segédmetódusok

    private GymInfo findGymInfo(Integer id) {
        return gymInfoRepository.getGymInfoById(id)
                .orElseThrow(() -> new ResourceNotFoundException("A terem adatai nem találhatók"));
    }

    private GymInfoResponse toResponse(GymInfo gymInfo) {
        return GymInfoResponse.builder()
                .id(gymInfo.getId())
                .gymName(gymInfo.getGymName())
                .address(gymInfo.getAddress())
                .phoneNumber(gymInfo.getPhoneNumber())
                .premiumMachineNumber(gymInfo.getPremiumMachineNumber())
                .email(gymInfo.getEmail())
                .openingHours(gymInfo.getOpeningHours())
                .usersNumber(gymInfo.getUsersNumber())
                .trainersNumber(gymInfo.getTrainersNumber())
                .updatedAt(gymInfo.getUpdatedAt())
                .build();
    }
}

//---------------------------------------------------------------------------------------------------------//