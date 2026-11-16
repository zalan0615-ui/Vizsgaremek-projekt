package com.gymforge.controller;

import com.gymforge.dto.request.CreateTrainerSchedulesRequest;
import com.gymforge.dto.request.UpdateTrainerSchedulesRequest;
import com.gymforge.dto.response.TrainerSchedulesResponse;
import com.gymforge.service.TrainerSchedulesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//----------------------------------------------------------------------------------------------//

@RestController
@RequestMapping("/api/trainer-schedules")
@RequiredArgsConstructor
public class TrainerSchedulesController {

    private final TrainerSchedulesService trainerSchedulesService;

    @PostMapping
    public ResponseEntity<TrainerSchedulesResponse> createTrainerSchedule(
            @Valid @RequestBody CreateTrainerSchedulesRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(trainerSchedulesService.createTrainerSchedule(request));
    }

    @GetMapping
    public ResponseEntity<List<TrainerSchedulesResponse>> getAllTrainerSchedules() {
        return ResponseEntity.ok(trainerSchedulesService.getAllTrainerSchedules());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainerSchedulesResponse> getTrainerScheduleById(@PathVariable Integer id) {
        return ResponseEntity.ok(trainerSchedulesService.getTrainerScheduleById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainerSchedulesResponse> updateTrainerSchedule(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateTrainerSchedulesRequest request) {
        return ResponseEntity.ok(trainerSchedulesService.updateTrainerSchedule(id, request));
    }
}

//----------------------------------------------------------------------------------------------//