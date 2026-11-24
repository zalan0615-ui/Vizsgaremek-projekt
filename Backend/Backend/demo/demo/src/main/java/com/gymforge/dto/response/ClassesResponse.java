package com.gymforge.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

//----------------------------------------------------------------------------------------------------//
//Classes response (edzésórák)

@Getter
@Builder
public class ClassesResponse {
    private Integer id;
    private String name;
    private Integer trainerId;
    private String description;
    private String durationMinutes;
    private String difficultyLevel;
    private String roomType;
    private LocalDateTime createdAt;
}