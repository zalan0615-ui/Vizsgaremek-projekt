package com.gymforge.repository;

import com.gymforge.model.ClassSchedules;
import com.gymforge.model.DayOfWeek;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

//----------------------------------------------------------------------------------------------------//
//----------------------------------------------------------------------------------------------------//

@Repository
@RequiredArgsConstructor
@SuppressWarnings("unchecked")
public class ClassSchedulesRepository {

    private final EntityManager entityManager;

//----------------------------------------------------------------------------------------------------//
//Create ClassSchedules

    public void createClassSchedule(Integer classId, DayOfWeek dayOfWeek,
                                    LocalTime startTime, LocalTime endTime) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("createclass_schedules");

        query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(3, Time.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(4, Time.class, ParameterMode.IN);

        query.setParameter(1, classId);
        query.setParameter(2, dayOfWeek.name());
        query.setParameter(3, Time.valueOf(startTime));
        query.setParameter(4, Time.valueOf(endTime));

        query.execute();
    }

//----------------------------------------------------------------------------------------------------//
//Get ClassSchedules

    public List<ClassSchedules> getAllClassSchedules() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("getclass_schedules", ClassSchedules.class);
        return query.getResultList();
    }

    public Optional<ClassSchedules> getClassScheduleById(Integer id) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("getByIdclass_schedules", ClassSchedules.class);

        query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        query.setParameter(1, id);

        List<ClassSchedules> result = query.getResultList();
        return result.stream().findFirst();
    }

//----------------------------------------------------------------------------------------------------//
//Update

    public void updateClassSchedule(Integer id, Integer classId, DayOfWeek dayOfWeek,
                                    LocalTime startTime, LocalTime endTime) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("updateclass_schedules");

        query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(4, Time.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(5, Time.class, ParameterMode.IN);

        query.setParameter(1, id);
        query.setParameter(2, classId);
        query.setParameter(3, dayOfWeek.name());
        query.setParameter(4, Time.valueOf(startTime));
        query.setParameter(5, Time.valueOf(endTime));

        query.execute();
    }
}

//----------------------------------------------------------------------------------------------------//

//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! NEM KELL SOFTDELET