package com.gymforge.controller;

import com.gymforge.dto.request.CreateClassSchedulesRequest;
import com.gymforge.dto.request.UpdateClassSchedulesRequest;
import com.gymforge.dto.response.ClassSchedulesResponse;
import com.gymforge.service.ClassSchedulesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/class-schedules")
@RequiredArgsConstructor
public class ClassSchedulesController {

    private final ClassSchedulesService classSchedulesService;

//---------------------------------------------------------------------------------------------------------//
//CreateClassSchedules
    @PostMapping
    public ResponseEntity<ClassSchedulesResponse> createClassSchedule(
            @Valid @RequestBody CreateClassSchedulesRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(classSchedulesService.createClassSchedule(request));
    }
    //---------------------------------------------------------------------------------------------------------//
//GetSchedules
    @GetMapping
    public ResponseEntity<List<ClassSchedulesResponse>> getAllClassSchedules() {
        return ResponseEntity.ok(classSchedulesService.getAllClassSchedules());
    }
    //---------------------------------------------------------------------------------------------------------//
//GetClassSchedulesById
    @GetMapping("/{id}")
    public ResponseEntity<ClassSchedulesResponse> getClassScheduleById(@PathVariable Integer id) {
        return ResponseEntity.ok(classSchedulesService.getClassScheduleById(id));
    }
    //---------------------------------------------------------------------------------------------------------//
//UpdateClassSchedules
    @PutMapping("/{id}")
    public ResponseEntity<ClassSchedulesResponse> updateClassSchedule(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateClassSchedulesRequest request) {
        return ResponseEntity.ok(classSchedulesService.updateClassSchedule(id, request));
    }
}