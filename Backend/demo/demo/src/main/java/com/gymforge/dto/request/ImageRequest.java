package com.gymforge.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageRequest {

    @NotBlank(message = "A kép linkje kötelező")
    @Size(max = 255, message = "A link maximum 255 karakter lehet")
    private String imageUrl;
}