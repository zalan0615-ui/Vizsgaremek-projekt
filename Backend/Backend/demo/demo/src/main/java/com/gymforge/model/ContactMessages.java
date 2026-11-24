package com.gymforge.model;

import jakarta.persistence.*;
import lombok.*;

import jakarta.persistence.Convert;

import java.time.LocalDateTime;

//----------------------------------------------------------------------------------------------------//
//ContactMessages class (kapcsolatfelvételi üzenetek)

@Entity
@Table(name = "contact_messages")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ContactMessages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Name", nullable = false, length = 150)
    private String name;

    @Column(name = "Email", nullable = false, length = 150)
    private String email;

    @Column(name = "Message", nullable = false, columnDefinition = "text")
    private String message;

    @Builder.Default
    @Convert(converter = ContactStatusConverter.class)
    @Column(name = "Status", nullable = false)
    private ContactStatus status = ContactStatus.NEW;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "Resolved_at")
    private LocalDateTime resolvedAt;

    @Column(name = "Admin_id")
    private Integer adminId;
}

//----------------------------------------------------------------------------------------------------//