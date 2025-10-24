package com.exercises.ejercicios_api.repository;

import com.exercises.ejercicios_api.model.TypeUserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeUserRepository extends JpaRepository<TypeUserModel, Long> {
}
