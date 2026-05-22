package com.example.scoapi.service;

import com.example.scoapi.exception.RegraNegocioException;
import com.example.scoapi.model.entity.ONG;
import com.example.scoapi.model.repository.ONGRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ONGService {

    private final ONGRepository repository;

    public ONGService(ONGRepository repository) {
        this.repository = repository;
    }

    public List<ONG> getOngs() {
        return repository.findAll();
    }

    public Optional<ONG> getOngById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public ONG salvar(ONG ong) {
        validar(ong);
        return repository.save(ong);
    }

    @Transactional
    public void excluir(ONG ong) {
        Objects.requireNonNull(ong.getId());
        repository.delete(ong);
    }

    public void validar(ONG ong) {
        if (ong == null) {
            throw new RegraNegocioException("ONG invalida");
        }
        if (isBlank(ong.getCnpj())) {
            throw new RegraNegocioException("CNPJ invalido");
        }
        if (isBlank(ong.getNome())) {
            throw new RegraNegocioException("Nome invalido");
        }
        if (isBlank(ong.getEmail())) {
            throw new RegraNegocioException("Email invalido");
        }
        if (isBlank(ong.getSenha())) {
            throw new RegraNegocioException("Senha invalida");
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
