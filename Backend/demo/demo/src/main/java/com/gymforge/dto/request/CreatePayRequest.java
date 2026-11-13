package com.gymforge.dto.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

//----------------------------------------------------------------------------------------------//

@Getter @Setter
public class CreatePayRequest {

    @NotNull(message = "A felhasználó azonosítója kötelező")
    private Integer userId;

    @NotNull(message = "A jegy azonosítója kötelező")
    private Integer membershipId;

    @NotNull(message = "Az ár megadása kötelező")
    private BigDecimal price;

    @NotBlank(message = "Az email megadása kötelező")
    @Email(message = "Nem érvényes email formátum")
    @Size(max = 150)
    private String email;
}

//----------------------------------------------------------------------------------------------//