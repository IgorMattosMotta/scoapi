package com.example.scoapi.model.repository;

import com.example.scoapi.model.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<AnimalRepository, Long> {
}