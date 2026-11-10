package com.gymforge.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

//----------------------------------------------------------------------------------------------------//
//GymInfo class (a terem alapadatai)

@Entity
@Table(name = "gym_info")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GymInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "gym_name", nullable = false, length = 100)
    private String gymName;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "phone_number", length = 50)
    private String phoneNumber;

    @Column(name = "premium_machine_number")
    private Integer premiumMachineNumber;

    @Column(name = "email", length = 150)
    private String email;

    // A "opening_hours" text típusú, ezért kell a columnDefinition
    @Column(name = "opening_hours", columnDefinition = "text")
    private String openingHours;

    @Column(name = "users_number")
    private Integer usersNumber;

    @Column(name = "trainers_number")
    private Integer trainersNumber;

    // Figyelem: itt "updated_at" a neve, nem "update_at" mint a többi táblában
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}

//----------------------------------------------------------------------------------------------------//