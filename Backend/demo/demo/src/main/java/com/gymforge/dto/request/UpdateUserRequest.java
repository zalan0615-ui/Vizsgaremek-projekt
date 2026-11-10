package com.gymforge.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRequest {

    @NotBlank(message = "A felhasználónév kötelező")
    @Size(max = 100, message = "Maximum 100 karakter")
    private String username;

    @NotBlank(message = "Az email kötelező")
    @Email(message = "Hibás email formátum")
    @Size(max = 150, message = "Maximum 150 karakter")
    private String email;
}