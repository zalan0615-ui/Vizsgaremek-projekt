package com.gymforge.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponse {
    private Integer id;
    private String username;
    private String email;
    private Boolean isActive;
}