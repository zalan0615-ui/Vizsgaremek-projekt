package com.gymforge.repository;

import com.gymforge.model.User;                         // a User entity (ebbe olvassa az eredményt)
import jakarta.persistence.EntityManager;               // a JPA fő eszköze az adatbázis eléréséhez
import jakarta.persistence.ParameterMode;               // paraméter iránya (IN = bemenet)
import jakarta.persistence.StoredProcedureQuery;        // tárolt eljárás hívása (nem SQL, hanem az eljárás neve)
import lombok.RequiredArgsConstructor;                  // konstruktort csinál a final mezőből
import org.springframework.stereotype.Repository;       // jelzi: ez egy Repository

import java.util.List;
import java.util.Optional;

//---------------------------------------------------------------------------------------------------------//
//---------------------------------------------------------------------------------------------------------//

@Repository
@RequiredArgsConstructor
@SuppressWarnings("unchecked")
public class UserRepository {

    private final EntityManager entityManager;

//---------------------------------------------------------------------------------------------------------//
//Create User
    public void createUser(Integer imageId, String username, String email, String passwordHash) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("createuser");

        query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(4, String.class, ParameterMode.IN);

        query.setParameter(1, imageId);
        query.setParameter(2, username);
        query.setParameter(3, email);
        query.setParameter(4, passwordHash);

        query.execute();
    }
//---------------------------------------------------------------------------------------------------------//
//Get User


    public List<User> getAllUsers() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("getuser", User.class);
        return query.getResultList();
    }

    public Optional<User> getUserById(Integer id) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("getuserById", User.class);

        query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        query.setParameter(1, id);

        List<User> result = query.getResultList();
        return result.stream().findFirst();
    }

//---------------------------------------------------------------------------------------------------------//
//Update

    public void updateUser(Integer id, Integer imageId, String username, String email, Boolean isActive) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("updateuser");

        query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(4, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(5, Boolean.class, ParameterMode.IN);

        query.setParameter(1, id);
        query.setParameter(2, imageId);
        query.setParameter(3, username);
        query.setParameter(4, email);
        query.setParameter(5, isActive);

        query.execute();
    }

//---------------------------------------------------------------------------------------------------------//
//Soft Delete User

    public void softDeleteUser(Integer id) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("softDeleteuser");

        query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        query.setParameter(1, id);

        query.execute();
    }
}

//---------------------------------------------------------------------------------------------------------//