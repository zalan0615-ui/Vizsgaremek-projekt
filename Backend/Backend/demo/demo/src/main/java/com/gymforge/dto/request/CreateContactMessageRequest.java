package com.gymforge.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

//----------------------------------------------------------------------------------------------------//

@Getter
@Setter
public class CreateContactMessageRequest {

    @NotBlank(message = "A név megadása kötelező")
    @Size(max = 150)
    private String name;

    @NotBlank(message = "Az email megadása kötelező")
    @Email(message = "Nem érvényes email formátum")
    @Size(max = 150)
    private String email;

    @NotBlank(message = "Az üzenet megadása kötelező")
    @Size(max = 2000, message = "Az üzenet legfeljebb 2000 karakter lehet")
    private String message;
}

//----------------------------------------------------------------------------------------------------//