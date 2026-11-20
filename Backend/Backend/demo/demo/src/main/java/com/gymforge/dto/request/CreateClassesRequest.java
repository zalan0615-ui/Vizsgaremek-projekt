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

    //Ha nincs név
    @NotNull(message = "Az óra neve kötelező")
    private ClassName name;

    //Ha nincs edző hivatkozva
    @NotNull(message = "Az edző megadása kötelező")
    private Integer trainerId;

    private String description;

    //Ha nincs meg, hogy meddig tart
    @NotBlank(message = "Az időtartam megadása kötelező")
    @Size(max = 50)
    private String durationMinutes;

    //Ha nincs nehézségi szint
    @NotBlank(message = "A nehézségi szint megadása kötelező")
    @Size(max = 50)
    private String difficultyLevel;

    //Ha nincs terem
    @NotNull(message = "A terem típusa kötelező")
    private RoomType roomType;
}