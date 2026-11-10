package com.gymforge.repository;

import com.gymforge.model.Image;
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
public class ImageRepository {

    private final EntityManager entityManager;

//----------------------------------------------------------------------------------------------------//
//Create Image

    public void createImage(String imageUrl) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("createimages");

        query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        query.setParameter(1, imageUrl);

        query.execute();
    }

//----------------------------------------------------------------------------------------------------//
//Get Image

    public List<Image> getAllImages() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("getimages", Image.class);
        return query.getResultList();
    }

    public Optional<Image> getImageById(Integer id) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("getByIdimages", Image.class);

        query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        query.setParameter(1, id);

        List<Image> result = query.getResultList();
        return result.stream().findFirst();
    }

//----------------------------------------------------------------------------------------------------//
//Update

    public void updateImage(Integer id, String imageUrl) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("updateimages");

        query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);

        query.setParameter(1, id);
        query.setParameter(2, imageUrl);

        query.execute();
        entityManager.clear();
    }

//----------------------------------------------------------------------------------------------------//
//Soft Delete Image

    public void softDeleteImage(Integer id) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("softDeleteimages");

        query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        query.setParameter(1, id);

        query.execute();
    }
}

//----------------------------------------------------------------------------------------------------//