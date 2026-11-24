package com.gymforge.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.LocalTime;

//----------------------------------------------------------------------------------------------------//
//ClassSchedules response (órarend)

@Getter
@Builder
public class ClassSchedulesResponse {
    private Integer id;
    private Integer classId;
    private String dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDateTime createdAt;
}