package com.example.scoapi.service;

import com.example.scoapi.exception.RegraNegocioException;
import com.example.scoapi.model.entity.SolicitacaoAdocao;
import com.example.scoapi.model.repository.SolicitacaoAdocaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SolicitacaoAdocaoService {

    private final SolicitacaoAdocaoRepository repository;

    public SolicitacaoAdocaoService(SolicitacaoAdocaoRepository repository) {
        this.repository = repository;
    }

    public List<SolicitacaoAdocao> getSolicitacoes() {
        return repository.findAll();
    }

    public Optional<SolicitacaoAdocao> getSolicitacaoById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public SolicitacaoAdocao salvar(SolicitacaoAdocao solicitacaoAdocao) {
        validar(solicitacaoAdocao);
        return repository.save(solicitacaoAdocao);
    }

    @Transactional
    public void excluir(SolicitacaoAdocao solicitacaoAdocao) {
        Objects.requireNonNull(solicitacaoAdocao.getId());
        repository.delete(solicitacaoAdocao);
    }

    public void validar(SolicitacaoAdocao solicitacaoAdocao) {
        if (solicitacaoAdocao == null) {
            throw new RegraNegocioException("Solicitacao de adocao invalida");
        }
        if (solicitacaoAdocao.getDataSolicitacao() == null) {
            throw new RegraNegocioException("Data de solicitacao invalida");
        }
        if (solicitacaoAdocao.getStatus() == null) {
            throw new RegraNegocioException("Status invalido");
        }
        if (solicitacaoAdocao.getAdotante() == null) {
            throw new RegraNegocioException("Adotante invalido");
        }
        if (solicitacaoAdocao.getAnimal() == null) {
            throw new RegraNegocioException("Animal invalido");
        }
    }
}
