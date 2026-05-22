package com.example.scoapi.service;

import com.example.scoapi.exception.RegraNegocioException;
import com.example.scoapi.model.entity.RespostaQuestionario;
import com.example.scoapi.model.repository.RespostaQuestionarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class RespostaQuestionarioService {

    private final RespostaQuestionarioRepository repository;

    public RespostaQuestionarioService(RespostaQuestionarioRepository repository) {
        this.repository = repository;
    }

    public List<RespostaQuestionario> getRespostas() {
        return repository.findAll();
    }

    public Optional<RespostaQuestionario> getRespostaById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public RespostaQuestionario salvar(RespostaQuestionario respostaQuestionario) {
        validar(respostaQuestionario);
        return repository.save(respostaQuestionario);
    }

    @Transactional
    public void excluir(RespostaQuestionario respostaQuestionario) {
        Objects.requireNonNull(respostaQuestionario.getId());
        repository.delete(respostaQuestionario);
    }

    public void validar(RespostaQuestionario respostaQuestionario) {
        if (respostaQuestionario == null) {
            throw new RegraNegocioException("Resposta de questionario invalida");
        }
        if (isBlank(respostaQuestionario.getConteudo())) {
            throw new RegraNegocioException("Conteudo invalido");
        }
        if (respostaQuestionario.getSolicitacao() == null) {
            throw new RegraNegocioException("Solicitacao invalida");
        }
        if (respostaQuestionario.getPergunta() == null) {
            throw new RegraNegocioException("Pergunta invalida");
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
