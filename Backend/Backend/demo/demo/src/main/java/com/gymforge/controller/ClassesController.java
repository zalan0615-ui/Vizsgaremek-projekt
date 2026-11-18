package com.gymforge.controller;

import com.gymforge.dto.request.CreateClassesRequest;
import com.gymforge.dto.request.UpdateClassesRequest;
import com.gymforge.dto.response.ClassesResponse;
import com.gymforge.service.ClassesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
@RequiredArgsConstructor
public class ClassesController {

    private final ClassesService classesService;

    @PostMapping
    public ResponseEntity<ClassesResponse> createClass(@Valid @RequestBody CreateClassesRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(classesService.createClass(request));
    }

    @GetMapping
    public ResponseEntity<List<ClassesResponse>> getAllClasses() {
        return ResponseEntity.ok(classesService.getAllClasses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassesResponse> getClassById(@PathVariable Integer id) {
        return ResponseEntity.ok(classesService.getClassById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassesResponse> updateClass(@PathVariable Integer id,
                                                       @Valid @RequestBody UpdateClassesRequest request) {
        return ResponseEntity.ok(classesService.updateClass(id, request));
    }
}