package com.gymforge.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UpdateClassBookingsRequest {

    //Nincs hozzá rendelve Osztály
    @NotNull(message = "A felhasználó azonosítója kötelező")
    private Integer userId;

    //Nincs hozzá rendelve Osztályórarend
    @NotNull(message = "Az órarendi bejegyzés azonosítója kötelező")
    private Integer classScheduleId;

    //Nincs meg a foglalás időpontja
    @NotNull(message = "A foglalás időpontja kötelező")
    private LocalDateTime bookingDate;
}