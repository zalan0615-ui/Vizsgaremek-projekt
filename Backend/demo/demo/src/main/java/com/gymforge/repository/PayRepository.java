package com.gymforge.repository;

import com.gymforge.model.Pays;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

//----------------------------------------------------------------------------------------------//
//----------------------------------------------------------------------------------------------//

@Repository
@RequiredArgsConstructor
@SuppressWarnings("unchecked")
public class PayRepository {

    private final EntityManager entityManager;

//----------------------------------------------------------------------------------------------//
//Create Pay (az eljárás visszaadja az új id-t)

    public Integer createPay(Integer userId, Integer membershipId, BigDecimal price) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("createpaysWithId");

        query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(3, BigDecimal.class, ParameterMode.IN);

        query.setParameter(1, userId);
        query.setParameter(2, membershipId);
        query.setParameter(3, price);

        Object result = query.getResultList().get(0);
        return ((Number) result).intValue();
    }

//----------------------------------------------------------------------------------------------//
//Get Pay

    public List<Pays> getAllPays() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("getpays", Pays.class);
        return query.getResultList();
    }

    public Optional<Pays> getPayById(Integer id) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("getByIdpays", Pays.class);

        query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        query.setParameter(1, id);

        List<Pays> result = query.getResultList();
        return result.stream().findFirst();
    }

//----------------------------------------------------------------------------------------------//
//Email elküldve (email_sent = 1)

    public void markEmailSent(Integer id) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("markpaysEmailSent");

        query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        query.setParameter(1, id);

        query.execute();
    }
}

//----------------------------------------------------------------------------------------------//