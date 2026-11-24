package com.gymforge.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

//----------------------------------------------------------------------------------------------//

@Getter
@Setter
public class CreateTrainerSchedulesRequest {

    //Az edző megadása kötelező
    @NotNull(message = "Az edző azonosítója kötelező")
    private Integer trainerId;

    //A Nap megadása kötelező
    @NotNull(message = "A nap megadása kötelező")
    @Min(value = 1, message = "A nap értéke 1 és 7 között lehet")
    @Max(value = 7, message = "A nap értéke 1 és 7 között lehet")
    private Integer dayOfWeek;

    //A Kezdés megadása kötelező
    @NotNull(message = "A kezdési idő megadása kötelező")
    private LocalTime startTime;

    //Ha nincs meg a befejezése
    @NotNull(message = "A befejezési idő megadása kötelező")
    private LocalTime endTime;
}

//----------------------------------------------------------------------------------------------//