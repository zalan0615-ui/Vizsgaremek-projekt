package com.gymforge.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

//----------------------------------------------------------------------------------------------------//
//ClassBookings class (edzésórára való jelentkezések)

@Entity
@Table(name = "class_bookings")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ClassBookings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Ki jelentkezett (később @ManyToOne lesz a User felé)
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    // Melyik időpontra (később @ManyToOne lesz a ClassSchedules felé)
    @Column(name = "class_schedule_id", nullable = false)
    private Integer classScheduleId;

    // Alapértéke nincs az adatbázisban, ezt nekünk kell megadni mentéskor
    @Column(name = "booking_date", nullable = false)
    private LocalDateTime bookingDate;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;
}

//----------------------------------------------------------------------------------------------------//