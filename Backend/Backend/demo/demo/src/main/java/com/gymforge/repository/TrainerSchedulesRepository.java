package com.gymforge.repository;

import com.gymforge.model.TrainerSchedules;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

//----------------------------------------------------------------------------------------------//
//----------------------------------------------------------------------------------------------//

@Repository
@RequiredArgsConstructor
@SuppressWarnings("unchecked")
public class TrainerSchedulesRepository {

    private final EntityManager entityManager;

//----------------------------------------------------------------------------------------------//
//Create Trainer Schedule

    public void createTrainerSchedule(Integer trainerId, Integer dayOfWeek,
                                      LocalTime startTime, LocalTime endTime) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("createtrainer_schedules");

        query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(3, LocalTime.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(4, LocalTime.class, ParameterMode.IN);

        query.setParameter(1, trainerId);
        query.setParameter(2, dayOfWeek);
        query.setParameter(3, startTime);
        query.setParameter(4, endTime);

        query.execute();
    }

//----------------------------------------------------------------------------------------------//
//Get Trainer Schedule

    public List<TrainerSchedules> getAllTrainerSchedules() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("gettrainer_schedules", TrainerSchedules.class);
        return query.getResultList();
    }

    public Optional<TrainerSchedules> getTrainerScheduleById(Integer id) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("getByIdtrainer_schedules", TrainerSchedules.class);

        query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        query.setParameter(1, id);

        List<TrainerSchedules> result = query.getResultList();
        return result.stream().findFirst();
    }

//----------------------------------------------------------------------------------------------//
//Update Trainer Schedule

    public void updateTrainerSchedule(Integer id, Integer trainerId, Integer dayOfWeek,
                                      LocalTime startTime, LocalTime endTime) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("updatetrainer_schedules");

        query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(3, Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(4, LocalTime.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(5, LocalTime.class, ParameterMode.IN);

        query.setParameter(1, id);
        query.setParameter(2, trainerId);
        query.setParameter(3, dayOfWeek);
        query.setParameter(4, startTime);
        query.setParameter(5, endTime);

        query.execute();
    }
}

//----------------------------------------------------------------------------------------------//