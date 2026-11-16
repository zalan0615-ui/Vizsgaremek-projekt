package com.gymforge.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ImageResponse {
    private Integer id;
    private String imageUrl;
    private LocalDateTime createdAt;
}

