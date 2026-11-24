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
public class UpdateClassesRequest {

    //Nincs meg az óra neve.
    @NotNull(message = "Az óra neve kötelező")
    private ClassName name;

    //Nincs meg az edző
    @NotNull(message = "Az edző megadása kötelező")
    private Integer trainerId;

    private String description;

    // Nincs meg a időtartam.
    @NotBlank(message = "Az időtartam megadása kötelező")
    @Size(max = 50, message = "Maximum 50 karakter")
    private String durationMinutes;

    //Nincs meg a nehézségi szint
    @NotBlank(message = "A nehézségi szint megadása kötelező")
    @Size(max = 50, message = "Maximum 50 karakter")
    private String difficultyLevel;

    //Nincs meg a terem.
    @NotNull(message = "A terem típusa kötelező")
    private RoomType roomType;
}