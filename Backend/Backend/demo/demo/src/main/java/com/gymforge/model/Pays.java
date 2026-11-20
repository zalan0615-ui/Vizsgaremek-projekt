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

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "membership_id", nullable = false)
    private Integer membershipId;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Builder.Default
    @Convert(converter = PaymentStatusConverter.class)
    @Column(name = "status", nullable = false)
    private PaymentStatus status = PaymentStatus.UNPAID;

    @Builder.Default
    @Column(name = "email_sent", nullable = false)
    private Boolean emailSent = false;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "paid_at")
    private LocalDateTime paidAt;
}

//----------------------------------------------------------------------------------------------------//