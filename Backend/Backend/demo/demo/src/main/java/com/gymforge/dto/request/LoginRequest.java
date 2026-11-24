package com.gymforge.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

    @NotBlank(message = "Az email megadása kötelező")
    @Email(message = "Nem érvényes email formátum")
    private String email;

    @NotBlank(message = "A jelszó megadása kötelező")
    private String password;
}