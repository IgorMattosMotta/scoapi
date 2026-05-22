package com.example.scoapi.service;

import com.example.scoapi.exception.RegraNegocioException;
import com.example.scoapi.model.entity.StatusAnimal;
import com.example.scoapi.model.repository.StatusAnimalRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StatusAnimalService {

    private final StatusAnimalRepository repository;

    public StatusAnimalService(StatusAnimalRepository repository) {
        this.repository = repository;
    }

    public List<StatusAnimal> getStatus() {
        return repository.findAll();
    }

    public Optional<StatusAnimal> getStatusById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public StatusAnimal salvar(StatusAnimal statusAnimal) {
        validar(statusAnimal);
        return repository.save(statusAnimal);
    }

    @Transactional
    public void excluir(StatusAnimal statusAnimal) {
        Objects.requireNonNull(statusAnimal.getId());
        repository.delete(statusAnimal);
    }

    public void validar(StatusAnimal statusAnimal) {
        if (statusAnimal == null) {
            throw new RegraNegocioException("Status animal invalido");
        }
        if (isBlank(statusAnimal.getEstado())) {
            throw new RegraNegocioException("Estado invalido");
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
