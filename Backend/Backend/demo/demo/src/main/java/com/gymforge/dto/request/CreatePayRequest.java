package com.gymforge.dto.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

//----------------------------------------------------------------------------------------------//

@Getter @Setter
public class CreatePayRequest {

    //Ha nincs ki írta
    @NotNull(message = "A felhasználó azonosítója kötelező")
    private Integer userId;

    //Ha nincs meg melyik bérlet
    @NotNull(message = "A jegy azonosítója kötelező")
    private Integer membershipId;

    //Ha nincs meg az ár ENNEK UTÁNA NÉZNI!!!!!!
    @NotNull(message = "Az ár megadása kötelező")
    private BigDecimal price;

    //Ha nincs meg az Email
    @NotBlank(message = "Az email megadása kötelező")
    @Email(message = "Nem érvényes email formátum")
    @Size(max = 150)
    private String email;
}

//----------------------------------------------------------------------------------------------//