package com.gymforge.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

//---------------------------------------------------------------------------------------------------------//
//Behívások

@Entity
@Table(name = "log")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

//---------------------------------------------------------------------------------------------------------//
//Log class

public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "admin_id")
    private Integer adminId;

    @Column(name = "action", nullable = false, length = 100)
    private String action;

    @Column(name = "entity_table", length = 100)
    private String entityTable;

    @Column(name = "entity_id")
    private Integer entityId;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;
}