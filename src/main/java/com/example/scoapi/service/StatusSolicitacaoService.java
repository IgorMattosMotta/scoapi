package com.example.scoapi.service;

import com.example.scoapi.exception.RegraNegocioException;
import com.example.scoapi.model.entity.StatusSolicitacao;
import com.example.scoapi.model.repository.StatusSolicitacaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StatusSolicitacaoService {

    private final StatusSolicitacaoRepository repository;

    public StatusSolicitacaoService(StatusSolicitacaoRepository repository) {
        this.repository = repository;
    }

    public List<StatusSolicitacao> getStatus() {
        return repository.findAll();
    }

    public Optional<StatusSolicitacao> getStatusById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public StatusSolicitacao salvar(StatusSolicitacao statusSolicitacao) {
        validar(statusSolicitacao);
        return repository.save(statusSolicitacao);
    }

    @Transactional
    public void excluir(StatusSolicitacao statusSolicitacao) {
        Objects.requireNonNull(statusSolicitacao.getId());
        repository.delete(statusSolicitacao);
    }

    public void validar(StatusSolicitacao statusSolicitacao) {
        if (statusSolicitacao == null) {
            throw new RegraNegocioException("Status solicitacao invalido");
        }
        if (isBlank(statusSolicitacao.getStatus())) {
            throw new RegraNegocioException("Status invalido");
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
