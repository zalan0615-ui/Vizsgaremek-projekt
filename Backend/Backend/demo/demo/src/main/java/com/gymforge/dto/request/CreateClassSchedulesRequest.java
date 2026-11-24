package com.gymforge.dto.request;

import com.gymforge.model.DayOfWeek;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class CreateClassSchedulesRequest {

    //Ha nincs hivatkozva óra
    @NotNull(message = "Az óra azonosítója kötelező")
    private Integer classId;

    //Ha nincs meg mellyik nap
    @NotNull(message = "A nap megadása kötelező")
    private DayOfWeek dayOfWeek;

    //Ha nincs meg mikor kezdödik
    @NotNull(message = "A kezdési idő megadása kötelező")
    private LocalTime startTime;

    //Ha nincs meg mikor végzödik
    @NotNull(message = "A befejezési idő megadása kötelező")
    private LocalTime endTime;
}