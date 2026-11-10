package com.gymforge.repository;

import com.gymforge.model.Log;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@SuppressWarnings("unchecked")
public class LogRepository {

    private final EntityManager entityManager;

//----------------------------------------------------------------------------------------------------//
//Create Log

    public void createLog(Integer userId, Integer adminId, String action, String entityTable, Integer entityId) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("createlogs");

        query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(4, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(5, Integer.class, ParameterMode.IN);

        query.setParameter(1, userId);
        query.setParameter(2, adminId);
        query.setParameter(3, action);
        query.setParameter(4, entityTable);
        query.setParameter(5, entityId);

        query.execute();
    }

//----------------------------------------------------------------------------------------------------//
//Get Log

    public List<Log> getAllLogs() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("getlogs", Log.class);
        return query.getResultList();
    }

    public Optional<Log> getLogById(Integer id) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("getlogsById", Log.class);

        query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        query.setParameter(1, id);

        List<Log> result = query.getResultList();
        return result.stream().findFirst();
    }
}

//----------------------------------------------------------------------------------------------------//