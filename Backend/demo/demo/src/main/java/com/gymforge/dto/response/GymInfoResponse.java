package com.gymforge.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

//----------------------------------------------------------------------//
// GymInfoResponse (ezt kapja vissza a Postman / a frontend)

@Getter
@Builder
public class GymInfoResponse {
    private Integer id;
    private String gymName;
    private String address;
    private String phoneNumber;
    private Integer premiumMachineNumber;
    private String email;
    private String openingHours;
    private Integer usersNumber;
    private Integer trainersNumber;
    private LocalDateTime updatedAt;
}

//----------------------------------------------------------------------//