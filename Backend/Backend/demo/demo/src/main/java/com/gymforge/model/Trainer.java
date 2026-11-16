package com.gymforge.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

//----------------------------------------------------------------------------------------------------//
//Trainer class

@Entity
@Table(name = "trainer")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Figyelem: az adatbázisban nagy kezdőbetűvel van (Firstname, Lastname)
    @Column(name = "Firstname", nullable = false, length = 100)
    private String firstName;

    @Column(name = "Lastname", nullable = false, length = 100)
    private String lastName;

    @Column(name = "phone_number", length = 50)
    private String phoneNumber;

    @Column(name = "email", nullable = false, length = 150)
    private String email;

    // A "title" text típusú, ezért kell a columnDefinition
    @Column(name = "title", columnDefinition = "text")
    private String title;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "update_at", insertable = false, updatable = false)
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Builder.Default
    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;
}

//----------------------------------------------------------------------------------------------------//