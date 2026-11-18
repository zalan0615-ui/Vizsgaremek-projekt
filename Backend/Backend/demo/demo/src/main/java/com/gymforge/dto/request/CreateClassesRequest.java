package com.gymforge.dto.request;

import com.gymforge.model.ClassName;
import com.gymforge.model.RoomType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateClassesRequest {

    @NotNull(message = "Az óra neve kötelező")
    private ClassName name;

    @NotNull(message = "Az edző megadása kötelező")
    private Integer trainerId;

    private String description;

    @NotBlank(message = "Az időtartam megadása kötelező")
    @Size(max = 50)
    private String durationMinutes;

    @NotBlank(message = "A nehézségi szint megadása kötelező")
    @Size(max = 50)
    private String difficultyLevel;

    @NotNull(message = "A terem típusa kötelező")
    private RoomType roomType;
}