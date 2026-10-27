package com.gymforge.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

//----------------------------------------------------------------------------------------------------//
//Pays class (fizetések)

@Entity
@Table(name = "pays")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Pays {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Ki fizet (később @ManyToOne lesz a User felé)
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    // Melyik bérletet (később @ManyToOne lesz a Memberships felé)
    @Column(name = "membership_id", nullable = false)
    private Integer membershipId;

    // decimal(10,2): pénzösszegnél BigDecimal
    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private PaymentStatus status = PaymentStatus.UNPAID;

    // tinyint(1), alapérték 0: elment-e az értesítő email
    @Builder.Default
    @Column(name = "email_sent", nullable = false)
    private Boolean emailSent = false;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    // Akkor töltjük ki, amikor a fizetés "paid" lesz
    @Column(name = "paid_at")
    private LocalDateTime paidAt;
}

//----------------------------------------------------------------------------------------------------//