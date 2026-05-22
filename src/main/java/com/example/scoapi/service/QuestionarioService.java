package com.example.scoapi.service;

import com.example.scoapi.exception.RegraNegocioException;
import com.example.scoapi.model.entity.Questionario;
import com.example.scoapi.model.repository.QuestionarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class QuestionarioService {

    private final QuestionarioRepository repository;

    public QuestionarioService(QuestionarioRepository repository) {
        this.repository = repository;
    }

    public List<Questionario> getQuestionarios() {
        return repository.findAll();
    }

    public Optional<Questionario> getQuestionarioById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Questionario salvar(Questionario questionario) {
        validar(questionario);
        return repository.save(questionario);
    }

    @Transactional
    public void excluir(Questionario questionario) {
        Objects.requireNonNull(questionario.getId());
        repository.delete(questionario);
    }

    public void validar(Questionario questionario) {
        if (questionario == null) {
            throw new RegraNegocioException("Questionario invalido");
        }
        if (questionario.getOng() == null) {
            throw new RegraNegocioException("ONG invalida");
        }
    }
}
