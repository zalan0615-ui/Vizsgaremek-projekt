package com.gymforge.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

//----------------------------------------------------------------------//
// UpdateGymInfoRequest (a módosításhoz küldött adatok)

@Getter
@Setter
public class UpdateGymInfoRequest {

    @NotBlank(message = "A terem neve kötelező")
    @Size(max = 100, message = "Maximum 100 karakter")
    private String gymName;

    @NotBlank(message = "A cím kötelező")
    @Size(max = 255, message = "Maximum 255 karakter")
    private String address;

    @Size(max = 50, message = "Maximum 50 karakter")
    private String phoneNumber;

    @PositiveOrZero(message = "A prémium gépek száma nem lehet negatív")
    private Integer premiumMachineNumber;

    @Email(message = "Hibás email formátum")
    @Size(max = 150, message = "Maximum 150 karakter")
    private String email;

    private String openingHours;

    @PositiveOrZero(message = "A felhasználók száma nem lehet negatív")
    private Integer usersNumber;

    @PositiveOrZero(message = "Az edzők száma nem lehet negatív")
    private Integer trainersNumber;
}

//----------------------------------------------------------------------//