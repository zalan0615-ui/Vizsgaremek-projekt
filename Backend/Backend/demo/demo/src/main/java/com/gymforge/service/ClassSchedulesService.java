package com.gymforge.service;

import com.gymforge.dto.request.CreateClassSchedulesRequest;
import com.gymforge.dto.request.UpdateClassSchedulesRequest;
import com.gymforge.dto.response.ClassSchedulesResponse;
import com.gymforge.exception.ResourceNotFoundException;
import com.gymforge.model.ClassSchedules;
import com.gymforge.repository.ClassSchedulesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

//----------------------------------------------------------------------------------------------------//
//----------------------------------------------------------------------------------------------------//

@Service
@RequiredArgsConstructor
public class ClassSchedulesService {

    private final ClassSchedulesRepository classSchedulesRepository;

    //----------------------------------------------------------------------------
    // Létrehozás (createclass_schedules eljárás)

    @Transactional
    public ClassSchedulesResponse createClassSchedule(CreateClassSchedulesRequest request) {

        classSchedulesRepository.createClassSchedule(
                request.getClassId(),
                request.getDayOfWeek(),
                request.getStartTime(),
                request.getEndTime()
        );

        // Az eljárás nem ad vissza id-t, ezért a legnagyobb id-jú sort keressük vissza
        ClassSchedules saved = classSchedulesRepository.getAllClassSchedules().stream()
                .max(Comparator.comparing(ClassSchedules::getId))
                .orElseThrow(() -> new ResourceNotFoundException("Az órarendi bejegyzés létrehozása nem sikerült"));

        return toResponse(saved);
    }

    //----------------------------------------------------------------------------

    public List<ClassSchedulesResponse> getAllClassSchedules() {
        return classSchedulesRepository.getAllClassSchedules()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ClassSchedulesResponse getClassScheduleById(Integer id) {
        return toResponse(findClassSchedule(id));
    }

    //----------------------------------------------------------------------------
    // Módosítás (updateclass_schedules eljárás)

    @Transactional
    public ClassSchedulesResponse updateClassSchedule(Integer id, UpdateClassSchedulesRequest request) {
        findClassSchedule(id);

        classSchedulesRepository.updateClassSchedule(
                id,
                request.getClassId(),
                request.getDayOfWeek(),
                request.getStartTime(),
                request.getEndTime()
        );

        return toResponse(findClassSchedule(id));
    }

    //----------------------------------------------------------------------------
    // Segédmetódusok

    private ClassSchedules findClassSchedule(Integer id) {
        return classSchedulesRepository.getClassScheduleById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Az órarendi bejegyzés nem található"));
    }

    private ClassSchedulesResponse toResponse(ClassSchedules cs) {
        return ClassSchedulesResponse.builder()
                .id(cs.getId())
                .classId(cs.getClassId())
                .dayOfWeek(cs.getDayOfWeek() != null ? cs.getDayOfWeek().name() : null)
                .startTime(cs.getStartTime())
                .endTime(cs.getEndTime())
                .createdAt(cs.getCreatedAt())
                .build();
    }
}

//----------------------------------------------------------------------------------------------------//