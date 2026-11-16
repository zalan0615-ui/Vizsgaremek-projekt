package com.gymforge.dto.response;

import com.gymforge.model.ContactStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ContactMessagesResponse {
    private Integer id;
    private String name;                // Ki küldte az üzenetet
    private String email;               // Ide válaszolhatunk
    private String message;             // Az üzenet szövege
    private ContactStatus status;       // new / in_progress / resolved
    private LocalDateTime createdAt;    // Mikor érkezett
    private LocalDateTime resolvedAt;   // Mikor lett lezárva, lehet null
    private Integer adminId;            // Melyik admin kezelte, lehet null
}