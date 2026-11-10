package com.gymforge.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

//-------------------------------------------------------------------------//
//Behívások

@Entity
@Table(name = "trainer_schedules") // <--- Itt van megadva a tábla neve!
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

//-------------------------------------------------------------------------//
//TrainerSchedules class

public class TrainerSchedules {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "trainer_id", nullable = false)
    private Integer trainerId;

    @Column(name = "day_of_week", nullable = false)
    private Integer dayOfWeek;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private LocalDateTime updatedAt;

}