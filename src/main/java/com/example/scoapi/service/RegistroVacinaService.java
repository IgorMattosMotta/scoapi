package com.example.scoapi.service;

import com.example.scoapi.exception.RegraNegocioException;
import com.example.scoapi.model.entity.RegistroVacina;
import com.example.scoapi.model.repository.RegistroVacinaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class RegistroVacinaService {

    private final RegistroVacinaRepository repository;

    public RegistroVacinaService(RegistroVacinaRepository repository) {
        this.repository = repository;
    }

    public List<RegistroVacina> getRegistros() {
        return repository.findAll();
    }

    public Optional<RegistroVacina> getRegistroById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public RegistroVacina salvar(RegistroVacina registroVacina) {
        validar(registroVacina);
        return repository.save(registroVacina);
    }

    @Transactional
    public void excluir(RegistroVacina registroVacina) {
        Objects.requireNonNull(registroVacina.getId());
        repository.delete(registroVacina);
    }

    public void validar(RegistroVacina registroVacina) {
        if (registroVacina == null) {
            throw new RegraNegocioException("Registro de vacina invalido");
        }
        if (registroVacina.getDataAplicacao() == null) {
            throw new RegraNegocioException("Data de aplicacao invalida");
        }
        if (registroVacina.getDose() == null) {
            throw new RegraNegocioException("Dose invalida");
        }
        if (registroVacina.getProtocolo() == null) {
            throw new RegraNegocioException("Protocolo invalido");
        }
    }
}
