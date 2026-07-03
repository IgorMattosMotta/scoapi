package com.example.scoapi.model.repository;

import com.example.scoapi.model.entity.StatusSolicitacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatusSolicitacaoRepository extends JpaRepository<StatusSolicitacao, Long> {

    Optional<StatusSolicitacao> findByStatus(String status);
}