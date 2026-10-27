package com.gymforge.controller;

import com.gymforge.dto.request.ImageRequest;
import com.gymforge.dto.response.ImageResponse;
import com.gymforge.service.ImageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping
    public ResponseEntity<ImageResponse> create(@Valid @RequestBody ImageRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(imageService.createImage(request));
    }

    @GetMapping
    public ResponseEntity<List<ImageResponse>> getAll() {
        return ResponseEntity.ok(imageService.getAllImages());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImageResponse> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(imageService.getImageById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ImageResponse> update(@PathVariable Integer id,
                                                @Valid @RequestBody ImageRequest request) {
        return ResponseEntity.ok(imageService.updateImage(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        imageService.deleteImage(id);
        return ResponseEntity.noContent().build();
    }
}