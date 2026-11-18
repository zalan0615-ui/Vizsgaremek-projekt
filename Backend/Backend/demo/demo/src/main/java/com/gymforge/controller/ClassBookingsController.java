package com.gymforge.controller;

import com.gymforge.dto.request.CreateClassBookingsRequest;
import com.gymforge.dto.request.UpdateClassBookingsRequest;
import com.gymforge.dto.response.ClassBookingsResponse;
import com.gymforge.service.ClassBookingsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/class-bookings")
@RequiredArgsConstructor
public class ClassBookingsController {

    private final ClassBookingsService classBookingsService;

    @PostMapping
    public ResponseEntity<ClassBookingsResponse> createClassBooking(
            @Valid @RequestBody CreateClassBookingsRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(classBookingsService.createClassBooking(request));
    }

    @GetMapping
    public ResponseEntity<List<ClassBookingsResponse>> getAllClassBookings() {
        return ResponseEntity.ok(classBookingsService.getAllClassBookings());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassBookingsResponse> getClassBookingById(@PathVariable Integer id) {
        return ResponseEntity.ok(classBookingsService.getClassBookingById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassBookingsResponse> updateClassBooking(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateClassBookingsRequest request) {
        return ResponseEntity.ok(classBookingsService.updateClassBooking(id, request));
    }
}