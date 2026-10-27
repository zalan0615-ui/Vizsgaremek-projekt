package com.gymforge.repository;

import com.gymforge.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Integer> {

    List<Image> findByIsDeletedFalse();

    Optional<Image> findByIdAndIsDeletedFalse(Integer id);
}