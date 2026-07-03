package com.example.scoapi.service;

import com.example.scoapi.exception.RegraNegocioException;
import com.example.scoapi.model.entity.Animal;
import com.example.scoapi.model.entity.RegistroVacina;
import com.example.scoapi.model.repository.AnimalRepository;
import com.example.scoapi.model.repository.RegistroVacinaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AnimalService {

    private final AnimalRepository repository;
    private final RegistroVacinaRepository registroVacinaRepository;

    public AnimalService(AnimalRepository repository, RegistroVacinaRepository registroVacinaRepository) {
        this.repository = repository;
        this.registroVacinaRepository = registroVacinaRepository;
    }

    public List<Animal> getAnimais() {
        return repository.findAll();
    }

    public Optional<Animal> getAnimalById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Animal salvar(Animal animal) {
        validar(animal);
        return repository.save(animal);
    }

    @Transactional
    public void excluir(Animal animal) {
        Objects.requireNonNull(animal.getId());
        repository.delete(animal);
    }

    public List<RegistroVacina> consultarProntuario(Long animalId) {
        if (!repository.existsById(animalId)) {
            throw new RegraNegocioException("Animal não encontrado");
        }
        return registroVacinaRepository.findByAnimalId(animalId);
    }

    public void validar(Animal animal) {
        if (animal == null) {
            throw new RegraNegocioException("Animal invalido");
        }
        if (isBlank(animal.getNome())) {
            throw new RegraNegocioException("Nome invalido");
        }
        if (isBlank(animal.getEspecie())) {
            throw new RegraNegocioException("Especie invalida");
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
