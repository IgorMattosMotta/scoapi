package com.example.scoapi.service;

import com.example.scoapi.exception.RegraNegocioException;
import com.example.scoapi.model.entity.Adotante;
import com.example.scoapi.model.repository.AdotanteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AdotanteService {

    private final AdotanteRepository repository;

    public AdotanteService(AdotanteRepository repository) {
        this.repository = repository;
    }

    public List<Adotante> getAdotantes() {
        return repository.findAll();
    }

    public Optional<Adotante> getAdotanteById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Adotante salvar(Adotante adotante) {
        validar(adotante);
        return repository.save(adotante);
    }

    @Transactional
    public void excluir(Adotante adotante) {
        Objects.requireNonNull(adotante.getId());
        repository.delete(adotante);
    }

    public void validar(Adotante adotante) {
        if (adotante == null) {
            throw new RegraNegocioException("Adotante invalido");
        }
        if (isBlank(adotante.getCpf())) {
            throw new RegraNegocioException("CPF invalido");
        }
        if (isBlank(adotante.getNomeCompleto())) {
            throw new RegraNegocioException("Nome completo invalido");
        }
        if (isBlank(adotante.getEmail())) {
            throw new RegraNegocioException("Email invalido");
        }
        if (isBlank(adotante.getSenha())) {
            throw new RegraNegocioException("Senha invalida");
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
