package com.gymforge.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

//----------------------------------------------------------------------------------------------------//
//Memberships class (bérlet típusok)

@Entity
@Table(name = "memberships")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Memberships {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "duration_days")
    private Integer durationDays;

    @Column(name = "title", nullable = false, length = 150)
    private String title;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;
}

//----------------------------------------------------------------------------------------------------//