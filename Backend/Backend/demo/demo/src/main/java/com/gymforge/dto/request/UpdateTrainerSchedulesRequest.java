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
public class UpdateTrainerSchedulesRequest {

    @NotNull(message = "Az edző azonosítója kötelező")
    private Integer trainerId;

    @NotNull(message = "A nap megadása kötelező")
    @Min(value = 1, message = "A nap értéke 1 és 7 között lehet")
    @Max(value = 7, message = "A nap értéke 1 és 7 között lehet")
    private Integer dayOfWeek;

    @NotNull(message = "A kezdési idő megadása kötelező")
    private LocalTime startTime;

    @NotNull(message = "A befejezési idő megadása kötelező")
    private LocalTime endTime;
}

//----------------------------------------------------------------------------------------------//