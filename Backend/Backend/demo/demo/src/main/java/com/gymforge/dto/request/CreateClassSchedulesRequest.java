package com.gymforge.dto.request;

import com.gymforge.model.DayOfWeek;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class CreateClassSchedulesRequest {

    @NotNull(message = "Az óra azonosítója kötelező")
    private Integer classId;

    @NotNull(message = "A nap megadása kötelező")
    private DayOfWeek dayOfWeek;

    @NotNull(message = "A kezdési idő megadása kötelező")
    private LocalTime startTime;

    @NotNull(message = "A befejezési idő megadása kötelező")
    private LocalTime endTime;
}