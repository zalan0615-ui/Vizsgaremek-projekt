package com.gymforge.service;

import com.gymforge.dto.request.CreateTrainerSchedulesRequest;
import com.gymforge.dto.request.UpdateTrainerSchedulesRequest;
import com.gymforge.dto.response.TrainerSchedulesResponse;
import com.gymforge.exception.ResourceNotFoundException;
import com.gymforge.model.TrainerSchedules;
import com.gymforge.repository.TrainerSchedulesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//----------------------------------------------------------------------------------------------//
//----------------------------------------------------------------------------------------------//

@Service
@RequiredArgsConstructor
public class TrainerSchedulesService {

    private final TrainerSchedulesRepository trainerSchedulesRepository;

//-----------------------------------------------------------------------------
//CreateTrainerSchedules

    @Transactional
    public TrainerSchedulesResponse createTrainerSchedule(CreateTrainerSchedulesRequest request) {

        trainerSchedulesRepository.createTrainerSchedule(
                request.getTrainerId(),
                request.getDayOfWeek(),
                request.getStartTime(),
                request.getEndTime()
        );

        TrainerSchedules saved = trainerSchedulesRepository.getAllTrainerSchedules().stream()
                .filter(s -> s.getTrainerId().equals(request.getTrainerId())
                        && s.getDayOfWeek().equals(request.getDayOfWeek())
                        && s.getStartTime().equals(request.getStartTime())
                        && s.getEndTime().equals(request.getEndTime()))
                .max((a, b) -> a.getId().compareTo(b.getId()))
                .orElseThrow(() -> new ResourceNotFoundException("Az edzői órarend létrehozása nem sikerült"));

        return toResponse(saved);
    }

    //-----------------------------------------------------------------------------
//GetAllTrainerSchedules
    public List<TrainerSchedulesResponse> getAllTrainerSchedules() {
        return trainerSchedulesRepository.getAllTrainerSchedules()
                .stream()
                .map(this::toResponse)
                .toList();
    }
    //-----------------------------------------------------------------------------
//UpdateTrainerSchedules
    @Transactional
    public TrainerSchedulesResponse updateTrainerSchedule(Integer id, UpdateTrainerSchedulesRequest request) {
        findTrainerSchedule(id);

        trainerSchedulesRepository.updateTrainerSchedule(
                id,
                request.getTrainerId(),
                request.getDayOfWeek(),
                request.getStartTime(),
                request.getEndTime()
        );

        return toResponse(findTrainerSchedule(id));
    }
    //-----------------------------------------------------------------------------
//GetTrainerSchedulesById
    public TrainerSchedulesResponse getTrainerScheduleById(Integer id) {
        return toResponse(findTrainerSchedule(id));
    }

    //NEM KELL SOFT DELETE????

    //------------------------------------------------------------------------
    // Segédmetódusok

    private TrainerSchedules findTrainerSchedule(Integer id) {
        return trainerSchedulesRepository.getTrainerScheduleById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Az edzői órarend nem található"));
    }

    private TrainerSchedulesResponse toResponse(TrainerSchedules schedule) {
        return TrainerSchedulesResponse.builder()
                .id(schedule.getId())
                .trainerId(schedule.getTrainerId())
                .dayOfWeek(schedule.getDayOfWeek())
                .startTime(schedule.getStartTime())
                .endTime(schedule.getEndTime())
                .build();
    }
}

//----------------------------------------------------------------------------------------------//