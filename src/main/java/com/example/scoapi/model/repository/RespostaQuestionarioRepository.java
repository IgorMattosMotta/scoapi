package com.example.scoapi.model.repository;

import com.example.scoapi.model.entity.RespostaQuestionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RespostaQuestionarioRepository extends JpaRepository<RespostaQuestionario, Long> {
}