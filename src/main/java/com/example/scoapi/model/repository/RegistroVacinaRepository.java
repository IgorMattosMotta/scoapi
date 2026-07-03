package com.example.scoapi.model.repository;

import com.example.scoapi.model.entity.RegistroVacina;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegistroVacinaRepository extends JpaRepository<RegistroVacina, Long> {

    List<RegistroVacina> findByAnimalId(Long animalId);
}