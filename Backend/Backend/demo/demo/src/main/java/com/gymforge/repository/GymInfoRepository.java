package com.gymforge.repository;

import com.gymforge.model.GymInfo;                      // a GymInfo entity (ebbe olvassa az eredményt)
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
public class GymInfoRepository {

    private final EntityManager entityManager;

//---------------------------------------------------------------------------------------------------------//
//Get GymInfo

    public List<GymInfo> getAllGymInfo() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("getgym_info", GymInfo.class);
        return query.getResultList();
    }

    public Optional<GymInfo> getGymInfoById(Integer id) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("getByIdgym_info", GymInfo.class);

        query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        query.setParameter(1, id);

        List<GymInfo> result = query.getResultList();
        return result.stream().findFirst();
    }

//---------------------------------------------------------------------------------------------------------//
//Update

    public void updateGymInfo(Integer id, String gymName, String address, String phoneNumber,
                              Integer premiumMachineNumber, String email, String openingHours,
                              Integer usersNumber, Integer trainersNumber) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("updategym_info");

        query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(4, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(5, Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(6, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(7, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(8, Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(9, Integer.class, ParameterMode.IN);

        query.setParameter(1, id);
        query.setParameter(2, gymName);
        query.setParameter(3, address);
        query.setParameter(4, phoneNumber);
        query.setParameter(5, premiumMachineNumber);
        query.setParameter(6, email);
        query.setParameter(7, openingHours);
        query.setParameter(8, usersNumber);
        query.setParameter(9, trainersNumber);

        query.execute();

        // Kiürítjük a JPA gyorsítótárát, így az újra lekért sor már az új adatot hozza
        entityManager.clear();
    }
}

//---------------------------------------------------------------------------------------------------------//