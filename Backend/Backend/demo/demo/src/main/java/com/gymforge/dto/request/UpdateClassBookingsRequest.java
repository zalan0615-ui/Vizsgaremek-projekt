package com.gymforge.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UpdateClassBookingsRequest {

    @NotNull(message = "A felhasználó azonosítója kötelező")
    private Integer userId;

    @NotNull(message = "Az órarendi bejegyzés azonosítója kötelező")
    private Integer classScheduleId;

    @NotNull(message = "A foglalás időpontja kötelező")
    private LocalDateTime bookingDate;
}