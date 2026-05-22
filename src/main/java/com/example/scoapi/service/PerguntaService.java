package com.example.scoapi.service;

import com.example.scoapi.exception.RegraNegocioException;
import com.example.scoapi.model.entity.Pergunta;
import com.example.scoapi.model.repository.PerguntaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PerguntaService {

    private final PerguntaRepository repository;

    public PerguntaService(PerguntaRepository repository) {
        this.repository = repository;
    }

    public List<Pergunta> getPerguntas() {
        return repository.findAll();
    }

    public Optional<Pergunta> getPerguntaById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Pergunta salvar(Pergunta pergunta) {
        validar(pergunta);
        return repository.save(pergunta);
    }

    @Transactional
    public void excluir(Pergunta pergunta) {
        Objects.requireNonNull(pergunta.getId());
        repository.delete(pergunta);
    }

    public void validar(Pergunta pergunta) {
        if (pergunta == null) {
            throw new RegraNegocioException("Pergunta invalida");
        }
        if (isBlank(pergunta.getTexto())) {
            throw new RegraNegocioException("Texto invalido");
        }
        if (pergunta.getQuestionario() == null) {
            throw new RegraNegocioException("Questionario invalido");
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
