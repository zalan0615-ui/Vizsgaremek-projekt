package com.gymforge.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class LogResponse {
    private Integer id;
    private Integer userId;          // Ki csinálta (tag), lehet null
    private Integer adminId;         // Ki csinálta (admin), lehet null
    private String action;           // Mi történt, pl. USER_DELETED
    private String entityTable;      // Melyik táblát érintette, pl. user
    private Integer entityId;        // Melyik sort érintette
    private LocalDateTime createdAt; // Mikor történt
}