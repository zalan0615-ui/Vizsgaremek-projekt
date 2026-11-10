package com.gymforge.model;

import jakarta.persistence.*;
import lombok.*;

//-------------------------------------------------------------------------//
//Behívások

@Entity
@Table(name = "user_x_trainer")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

//-------------------------------------------------------------------------//
//UserXTrainer class

public class UserXTrainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "User_id", nullable = false)
    private Integer userId;

    @Column(name = "Trainer_id", nullable = false)
    private Integer trainerId;

}