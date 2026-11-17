package com.gymforge.repository;

import com.gymforge.model.UserTokens;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserTokensRepository {

    private final EntityManager entityManager;

    //--------------------------------------------------------------------------------//
    // Create UserTokens
    public void createUserTokens(Integer userId, String rememberToken, String resetPasswordToken,
                                 LocalDateTime resetPasswordExpires, LocalDateTime emailVerifiedAt) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("createuser_tokens");

        query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(4, LocalDateTime.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(5, LocalDateTime.class, ParameterMode.IN);

        query.setParameter(1, userId);
        query.setParameter(2, rememberToken);
        query.setParameter(3, resetPasswordToken);
        query.setParameter(4, resetPasswordExpires);
        query.setParameter(5, emailVerifiedAt);

        query.execute();
    }

    //--------------------------------------------------------------------------------//
    // Get UserTokens
    @SuppressWarnings("unchecked")
    public List<UserTokens> getAllUserTokens() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("getuser_tokens", UserTokens.class);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public Optional<UserTokens> getUserTokensById(Integer id) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("getByIduser_tokens", UserTokens.class);

        query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        query.setParameter(1, id);

        List<UserTokens> result = query.getResultList();
        return result.stream().findFirst();
    }

    //--------------------------------------------------------------------------------//
    // Update UserTokens
    public void updateUserTokens(Integer id, Integer userId, String rememberToken, String resetPasswordToken,
                                 LocalDateTime resetPasswordExpires, LocalDateTime emailVerifiedAt) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("updateuser_tokens");

        query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(4, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(5, LocalDateTime.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(6, LocalDateTime.class, ParameterMode.IN);

        query.setParameter(1, id);
        query.setParameter(2, userId);
        query.setParameter(3, rememberToken);
        query.setParameter(4, resetPasswordToken);
        query.setParameter(5, resetPasswordExpires);
        query.setParameter(6, emailVerifiedAt);

        query.execute();
        entityManager.clear();
    }
}