package com.gymforge.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalTime;

//----------------------------------------------------------------------------------------------//

@Getter
@Builder
public class TrainerSchedulesResponse {
    private Integer id;
    private Integer trainerId;
    private Integer dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
}

//----------------------------------------------------------------------------------------------//