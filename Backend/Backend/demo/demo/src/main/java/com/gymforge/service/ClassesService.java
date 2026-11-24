package com.gymforge.service;

import com.gymforge.dto.request.CreateClassesRequest;
import com.gymforge.dto.request.UpdateClassesRequest;
import com.gymforge.dto.response.ClassesResponse;
import com.gymforge.exception.ResourceNotFoundException;
import com.gymforge.model.Classes;
import com.gymforge.repository.ClassesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

//----------------------------------------------------------------------------------------------------//
//----------------------------------------------------------------------------------------------------//

@Service
@RequiredArgsConstructor
public class ClassesService {

    private final ClassesRepository classesRepository;


//-----------------------------------------------------------------------------
//CreateClasses

    @Transactional
    public ClassesResponse createClass(CreateClassesRequest request) {

        classesRepository.createClass(
                request.getName(),
                request.getTrainerId(),
                request.getDescription(),
                request.getDurationMinutes(),
                request.getDifficultyLevel(),
                request.getRoomType()
        );

        Classes saved = classesRepository.getAllClasses().stream()
                .max(Comparator.comparing(Classes::getId))
                .orElseThrow(() -> new ResourceNotFoundException("Az edzésóra létrehozása nem sikerült"));

        return toResponse(saved);
    }

//-----------------------------------------------------------------------------
//GetAllClasses

    public List<ClassesResponse> getAllClasses() {
        return classesRepository.getAllClasses()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ClassesResponse getClassById(Integer id) {
        return toResponse(findClass(id));
    }

//-----------------------------------------------------------------------------
//UpdateClasses

    @Transactional
    public ClassesResponse updateClass(Integer id, UpdateClassesRequest request) {
        findClass(id);

        classesRepository.updateClass(
                id,
                request.getName(),
                request.getTrainerId(),
                request.getDescription(),
                request.getDurationMinutes(),
                request.getDifficultyLevel(),
                request.getRoomType()
        );

        return toResponse(findClass(id));
    }

    //----------------------------------------------------------------------------
    // Segédmetódusok

    private Classes findClass(Integer id) {
        return classesRepository.getClassById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Az edzésóra nem található"));
    }

    private ClassesResponse toResponse(Classes classes) {
        return ClassesResponse.builder()
                .id(classes.getId())
                .name(classes.getName() != null ? classes.getName().name() : null)
                .trainerId(classes.getTrainerId())
                .description(classes.getDescription())
                .durationMinutes(classes.getDurationMinutes())
                .difficultyLevel(classes.getDifficultyLevel())
                .roomType(classes.getRoomType() != null ? classes.getRoomType().name() : null)
                .createdAt(classes.getCreatedAt())
                .build();
    }
}

//----------------------------------------------------------------------------------------------------//