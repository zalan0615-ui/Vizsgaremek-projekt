package com.gymforge.repository;

import com.gymforge.model.Admin;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//---------------------------------------------------------------------------------------------------------//
//---------------------------------------------------------------------------------------------------------//

@Repository
@RequiredArgsConstructor
@SuppressWarnings("unchecked")
public class AdminRepository {

    private final EntityManager entityManager;

//---------------------------------------------------------------------------------------------------------//
//Create Admin

    public void createAdmin(Integer imageId, String username, String email, String passwordHash) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("createadmins");

        query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(4, String.class, ParameterMode.IN);

        query.setParameter(1, imageId);
        query.setParameter(2, email);
        query.setParameter(3, passwordHash);
        query.setParameter(4, username);

        query.execute();
    }

//---------------------------------------------------------------------------------------------------------//
//Get Admin

    // getadmins eljárás: a nem törölt adminok listája
    public List<Admin> getAllAdmins() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("getadmins", Admin.class);
        return query.getResultList(); // lefuttatja, és visszaadja az összes sort
    }

    // getadminsById eljárás. Optional: lehet, hogy nincs találat
    public Optional<Admin> getAdminById(Integer id) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("getadminsById", Admin.class);

        query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        query.setParameter(1, id);
        List<Admin> result = query.getResultList();
        return result.stream().findFirst();
    }

//---------------------------------------------------------------------------------------------------------//
//Update Admin


    public void updateAdmin(Integer id, Integer imageId, String username, String email, Boolean isActive) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("updateadmins");

        query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN); // 1. paraméter: idIN (INT)
        query.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN); // 2. paraméter: image_idIN (INT)
        query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);  // 3. paraméter: emailIN (VARCHAR 150)
        query.registerStoredProcedureParameter(4, String.class, ParameterMode.IN);  // 4. paraméter: usernameIN (VARCHAR 100)
        query.registerStoredProcedureParameter(5, Boolean.class, ParameterMode.IN); // 5. paraméter: is_activeIN (TINYINT)

        query.setParameter(1, id);
        query.setParameter(2, imageId);
        query.setParameter(3, email);
        query.setParameter(4, username);
        query.setParameter(5, isActive);

        query.execute();
    }

//---------------------------------------------------------------------------------------------------------//
//Soft Delete Admin


    public void softDeleteAdmin(Integer id) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("softDeleteadmins");

        query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        query.setParameter(1, id);

        query.execute();
    }
}

//---------------------------------------------------------------------------------------------------------//