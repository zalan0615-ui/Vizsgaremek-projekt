package com.gymforge.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

//----------------------------------------------------------------------------------------------------//
//ClassBookings response (óra foglalások)

@Getter
@Builder
public class ClassBookingsResponse {
    private Integer id;
    private Integer userId;
    private Integer classScheduleId;
    private LocalDateTime bookingDate;
    private LocalDateTime createdAt;
}