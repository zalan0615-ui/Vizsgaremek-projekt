package com.gymforge.service;

import com.gymforge.dto.request.ImageRequest;
import com.gymforge.dto.response.ImageResponse;
import com.gymforge.exception.ResourceNotFoundException;
import com.gymforge.model.Image;
import com.gymforge.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    @Transactional
    public ImageResponse createImage(ImageRequest request) {
        Image image = Image.builder()
                .imageUrl(request.getImageUrl())
                .isDeleted(false)
                .build();

        Image saved = imageRepository.save(image);
        return toResponse(saved);
    }

    public List<ImageResponse> getAllImages() {
        return imageRepository.findByIsDeletedFalse()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ImageResponse getImageById(Integer id) {
        return toResponse(findActiveImage(id));
    }

    @Transactional
    public ImageResponse updateImage(Integer id, ImageRequest request) {
        Image image = findActiveImage(id);
        image.setImageUrl(request.getImageUrl());
        Image saved = imageRepository.save(image);
        return toResponse(saved);
    }

    @Transactional
    public void deleteImage(Integer id) {
        Image image = findActiveImage(id);
        image.setIsDeleted(true);
        image.setDeletedAt(LocalDateTime.now());
        imageRepository.save(image);
    }

    // Segédmetódusok

    private Image findActiveImage(Integer id) {
        return imageRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("A kép nem található"));
    }

    private ImageResponse toResponse(Image image) {
        return ImageResponse.builder()
                .id(image.getId())
                .imageUrl(image.getImageUrl())
                .createdAt(image.getCreatedAt())
                .build();
    }
}