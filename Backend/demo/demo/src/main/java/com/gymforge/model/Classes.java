package com.gymforge.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

//----------------------------------------------------------------------------------------------------//
//GymClass class (edzésórák)

@Entity
@Table(name = "classes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Classes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private ClassName name;

    @Column(name = "trainer_id")
    private Integer trainerId;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "duration_minutes", nullable = false, length = 50)
    private String durationMinutes;

    @Column(name = "difficulty_level", nullable = false, length = 50)
    private String difficultyLevel;

    @Enumerated(EnumType.STRING)
    @Column(name = "room_type")
    private RoomType roomType;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;
}