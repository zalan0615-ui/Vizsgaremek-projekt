package com.gymforge.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class UserTokensResponse {
    private Integer id;
    private Integer userId;
    private String rememberToken;
    private String resetPasswordToken;
    private LocalDateTime resetPasswordExpires;
    private LocalDateTime emailVerifiedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}