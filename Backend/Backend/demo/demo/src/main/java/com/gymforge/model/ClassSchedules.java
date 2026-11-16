package com.gymforge.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

//----------------------------------------------------------------------------------------------------//
//ClassSchedules class (edzésórák heti időpontjai)

@Entity
@Table(name = "class_schedules")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ClassSchedules {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Melyik edzésóra (később @ManyToOne lesz a Classes/GymClass felé)
    @Column(name = "class_id", nullable = false)
    private Integer classId;

    // Az adatbázisban enum('monday','tuesday','wednesday','thursday',...), itt egyelőre String
    @Column(name = "day_of_week", nullable = false)
    private String dayOfWeek;

    // A "time" típus Java-ban LocalTime (csak óra:perc, dátum nélkül)
    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;
}

//----------------------------------------------------------------------------------------------------//