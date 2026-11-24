package com.gymforge.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class LogResponse {
    private Integer id;
    private Integer userId;
    private Integer adminId;
    private String action;
    private String entityTable;
    private Integer entityId;
    private LocalDateTime createdAt;
}