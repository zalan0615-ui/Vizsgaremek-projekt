package com.gymforge.dto.request;

import com.gymforge.model.DayOfWeek;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class UpdateClassSchedulesRequest {

    //Nincs meg az óraId
    @NotNull(message = "Az óra azonosítója kötelező")
    private Integer classId;

    //Nincs meg a nap
    @NotNull(message = "A nap megadása kötelező")
    private DayOfWeek dayOfWeek;

    //Nincs meg a kezdés
    @NotNull(message = "A kezdési idő megadása kötelező")
    private LocalTime startTime;

    //Nincs meg a befejezés ideje
    @NotNull(message = "A befejezési idő megadása kötelező")
    private LocalTime endTime;
}