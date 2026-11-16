package com.gymforge.service;

import com.gymforge.dto.request.ImageRequest;
import com.gymforge.dto.response.ImageResponse;
import com.gymforge.exception.ResourceNotFoundException;
import com.gymforge.model.Image;
import com.gymforge.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    //---------------------------------------------------------------------------
    // Létrehozás (createimages eljárás)

    @Transactional
    public ImageResponse createImage(ImageRequest request) {

        imageRepository.createImage(request.getImageUrl());

        Image saved = imageRepository.getAllImages().stream()
                .filter(i -> i.getImageUrl().equals(request.getImageUrl()))
                .max(Comparator.comparing(Image::getId))
                .orElseThrow(() -> new ResourceNotFoundException("A kép létrehozása nem sikerült"));

        return toResponse(saved);
    }

    //---------------------------------------------------------------------------
    // Lekérdezés (getimages, getByIdimages eljárás)

    public List<ImageResponse> getAllImages() {
        return imageRepository.getAllImages()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ImageResponse getImageById(Integer id) {
        return toResponse(findActiveImage(id));
    }

    //---------------------------------------------------------------------------
    // Módosítás (updateimages eljárás)

    @Transactional
    public ImageResponse updateImage(Integer id, ImageRequest request) {
        findActiveImage(id);

        imageRepository.updateImage(id, request.getImageUrl());

        return toResponse(findActiveImage(id));
    }

    //---------------------------------------------------------------------------
    // Törlés (softDeleteimages eljárás)

    @Transactional
    public void deleteImage(Integer id) {
        findActiveImage(id);
        imageRepository.softDeleteImage(id);
    }

    //---------------------------------------------------------------------------
    // Segédmetódusok

    private Image findActiveImage(Integer id) {
        return imageRepository.getImageById(id)
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

//----------------------------------------------------------------------------------------------------//