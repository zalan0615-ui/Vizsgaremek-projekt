package com.gymforge.model;

import jakarta.persistence.*;
import lombok.*;

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

    // Figyelem: az adatbázisban nagy kezdőbetűvel van (Name, Email, Message, Status)
    @Column(name = "Name", nullable = false, length = 150)
    private String name;

    @Column(name = "Email", nullable = false, length = 150)
    private String email;

    @Column(name = "Message", nullable = false, columnDefinition = "text")
    private String message;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "Status", nullable = false)
    private ContactStatus status = ContactStatus.NEW;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    // Akkor töltjük ki, amikor az üzenet "resolved" lesz
    @Column(name = "Resolved_at")
    private LocalDateTime resolvedAt;

    // Melyik admin kezelte (később @ManyToOne lesz az Admin felé)
    @Column(name = "Admin_id")
    private Integer adminId;
}

//----------------------------------------------------------------------------------------------------//