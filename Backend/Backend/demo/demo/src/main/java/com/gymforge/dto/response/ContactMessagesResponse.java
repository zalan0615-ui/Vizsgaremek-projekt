package com.gymforge.dto.response;

import com.gymforge.model.ContactStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ContactMessagesResponse {
    private Integer id;
    private String name;
    private String email;
    private String message;
    private ContactStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime resolvedAt;
    private Integer adminId;
}