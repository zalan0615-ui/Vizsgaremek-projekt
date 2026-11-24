package com.gymforge.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreateClassBookingsRequest {

    //Ha nincs UserID
    @NotNull(message = "A felhasználó azonosítója kötelező")
    private Integer userId;

    //Ha nincs ClassScheduleId!!!!
    @NotNull(message = "Az órarendi bejegyzés azonosítója kötelező")
    private Integer classScheduleId;

    //Ha nincs foglalás
    @NotNull(message = "A foglalás időpontja kötelező")
    private LocalDateTime bookingDate;
}