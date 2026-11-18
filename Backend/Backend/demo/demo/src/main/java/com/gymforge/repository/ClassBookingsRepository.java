package com.gymforge.repository;

import com.gymforge.model.ClassBookings;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

//----------------------------------------------------------------------------------------------------//
//----------------------------------------------------------------------------------------------------//

@Repository
@RequiredArgsConstructor
@SuppressWarnings("unchecked")
public class ClassBookingsRepository {

    private final EntityManager entityManager;

//----------------------------------------------------------------------------------------------------//
//Create ClassBookings

    public void createClassBooking(Integer userId, Integer classScheduleId, LocalDateTime bookingDate) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("createclass_bookings");

        query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(3, Timestamp.class, ParameterMode.IN);

        query.setParameter(1, userId);
        query.setParameter(2, classScheduleId);
        query.setParameter(3, Timestamp.valueOf(bookingDate));

        query.execute();
    }

//----------------------------------------------------------------------------------------------------//
//Get ClassBookings

    public List<ClassBookings> getAllClassBookings() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("getclass_bookings", ClassBookings.class);
        return query.getResultList();
    }

    public Optional<ClassBookings> getClassBookingById(Integer id) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("getByIdclass_bookings", ClassBookings.class);

        query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        query.setParameter(1, id);

        List<ClassBookings> result = query.getResultList();
        return result.stream().findFirst();
    }

//----------------------------------------------------------------------------------------------------//
//Update

    public void updateClassBooking(Integer id, Integer userId, Integer classScheduleId, LocalDateTime bookingDate) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("updateclass_bookings");

        query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(3, Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(4, Timestamp.class, ParameterMode.IN);

        query.setParameter(1, id);
        query.setParameter(2, userId);
        query.setParameter(3, classScheduleId);
        query.setParameter(4, Timestamp.valueOf(bookingDate));

        query.execute();
    }
}

//----------------------------------------------------------------------------------------------------//