package com.gymforge.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

//-------------------------------------------------------------------------//
//Behívások

@Entity
@Table(name = "user_memberships")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

//-------------------------------------------------------------------------//
//UserMemberships class

public class UserMemberships {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "membership_id")
    private Integer membershipId;

    @Column(name = "pays_id")
    private Integer paysId;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private MembershipStatus status;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

}