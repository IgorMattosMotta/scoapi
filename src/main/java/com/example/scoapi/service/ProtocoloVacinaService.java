package com.example.scoapi.service;

import com.example.scoapi.exception.RegraNegocioException;
import com.example.scoapi.model.entity.ProtocoloVacina;
import com.example.scoapi.model.repository.ProtocoloVacinaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProtocoloVacinaService {

    private final ProtocoloVacinaRepository repository;

    public ProtocoloVacinaService(ProtocoloVacinaRepository repository) {
        this.repository = repository;
    }

    public List<ProtocoloVacina> getProtocolos() {
        return repository.findAll();
    }

    public Optional<ProtocoloVacina> getProtocoloById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public ProtocoloVacina salvar(ProtocoloVacina protocoloVacina) {
        validar(protocoloVacina);
        return repository.save(protocoloVacina);
    }

    @Transactional
    public void excluir(ProtocoloVacina protocoloVacina) {
        Objects.requireNonNull(protocoloVacina.getId());
        repository.delete(protocoloVacina);
    }

    public void validar(ProtocoloVacina protocoloVacina) {
        if (protocoloVacina == null) {
            throw new RegraNegocioException("Protocolo de vacina invalido");
        }
        if (isBlank(protocoloVacina.getNomeVacina())) {
            throw new RegraNegocioException("Nome da vacina invalido");
        }
        if (protocoloVacina.getQuantidadeDoses() == null || protocoloVacina.getQuantidadeDoses() <= 0) {
            throw new RegraNegocioException("Quantidade de doses invalida");
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
