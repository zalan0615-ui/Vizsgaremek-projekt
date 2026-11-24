package com.gymforge.repository;

import com.gymforge.model.ClassName;
import com.gymforge.model.Classes;
import com.gymforge.model.RoomType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//----------------------------------------------------------------------------------------------------//
//----------------------------------------------------------------------------------------------------//

@Repository
@RequiredArgsConstructor
@SuppressWarnings("unchecked")
public class ClassesRepository {

    private final EntityManager entityManager;

//----------------------------------------------------------------------------------------------------//
//Create Classes

    public void createClass(ClassName name, Integer trainerId, String description,
                            String durationMinutes, String difficultyLevel, RoomType roomType) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("createclasses");

        query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(4, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(5, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(6, String.class, ParameterMode.IN);

        query.setParameter(1, name.name());
        query.setParameter(2, trainerId);
        query.setParameter(3, description);
        query.setParameter(4, durationMinutes);
        query.setParameter(5, difficultyLevel);
        query.setParameter(6, roomType.name());

        query.execute();
    }

//----------------------------------------------------------------------------------------------------//
//Get Classes

    public List<Classes> getAllClasses() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("getclasses", Classes.class);
        return query.getResultList();
    }

    public Optional<Classes> getClassById(Integer id) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("getByIdclasses", Classes.class);

        query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        query.setParameter(1, id);

        List<Classes> result = query.getResultList();
        return result.stream().findFirst();
    }

//----------------------------------------------------------------------------------------------------//
//Update

    public void updateClass(Integer id, ClassName name, Integer trainerId, String description,
                            String durationMinutes, String difficultyLevel, RoomType roomType) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("updateclasses");

        query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(3, Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(4, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(5, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(6, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(7, String.class, ParameterMode.IN);

        query.setParameter(1, id);
        query.setParameter(2, name.name());
        query.setParameter(3, trainerId);
        query.setParameter(4, description);
        query.setParameter(5, durationMinutes);
        query.setParameter(6, difficultyLevel);
        query.setParameter(7, roomType.name());

        query.execute();
    }
}

//----------------------------------------------------------------------------------------------------//