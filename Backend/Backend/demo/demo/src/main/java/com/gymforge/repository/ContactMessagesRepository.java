package com.gymforge.repository;

import com.gymforge.model.ContactMessages;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

//----------------------------------------------------------------------------------------------------//

@Repository
@RequiredArgsConstructor
@SuppressWarnings("unchecked")
public class ContactMessagesRepository {

    private final EntityManager entityManager;

//----------------------------------------------------------------------------------------------------//
//Create Contact Message

    public void createContactMessage(String name, String email, String message,
                                     String status, LocalDateTime resolvedAt, Integer adminId) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("createcontact_messages");

        query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(4, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(5, LocalDateTime.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(6, Integer.class, ParameterMode.IN);

        query.setParameter(1, name);
        query.setParameter(2, email);
        query.setParameter(3, message);
        query.setParameter(4, status);
        query.setParameter(5, resolvedAt);
        query.setParameter(6, adminId);

        query.execute();
    }

//----------------------------------------------------------------------------------------------------//
//Get Contact Message

    public List<ContactMessages> getAllContactMessages() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("getcontact_messages", ContactMessages.class);
        return query.getResultList();
    }

    public Optional<ContactMessages> getContactMessageById(Integer id) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("getByIdcontact_messages", ContactMessages.class);

        query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        query.setParameter(1, id);

        List<ContactMessages> result = query.getResultList();
        return result.stream().findFirst();
    }

//----------------------------------------------------------------------------------------------------//
//Update Contact Message

    public void updateContactMessage(Integer id, String name, String email, String message,
                                     String status, LocalDateTime resolvedAt, Integer adminId) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("updatecontact_messages");

        query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(4, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(5, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(6, LocalDateTime.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(7, Integer.class, ParameterMode.IN);

        query.setParameter(1, id);
        query.setParameter(2, name);
        query.setParameter(3, email);
        query.setParameter(4, message);
        query.setParameter(5, status);
        query.setParameter(6, resolvedAt);
        query.setParameter(7, adminId);

        query.execute();
    }

//----------------------------------------------------------------------------------------------------//
}