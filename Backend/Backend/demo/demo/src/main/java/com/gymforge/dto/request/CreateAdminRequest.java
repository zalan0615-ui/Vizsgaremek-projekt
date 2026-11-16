package com.gymforge.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

//---------------------------------------------------------------------------------------------------------//
//---------------------------------------------------------------------------------------------------------//

@Getter
@Setter
public class CreateAdminRequest {

    @NotBlank(message = "A név megadása kötelező")
    @Size(max = 100)
    private String username;

    @NotBlank(message = "A email megadása kötelező")
    @Email(message = "Nem érvényes email formátum")
    @Size(max = 150)
    private String email;

    @NotBlank(message = "A jelszó megadása kötelező")
    @Size(min = 8, max = 100)
    private String password;
}

//---------------------------------------------------------------------------------------------------------//