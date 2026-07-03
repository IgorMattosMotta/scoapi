package com.example.scoapi.service;

import com.example.scoapi.exception.RegraNegocioException;
import com.example.scoapi.model.entity.Pergunta;
import com.example.scoapi.model.entity.RespostaQuestionario;
import com.example.scoapi.model.entity.SolicitacaoAdocao;
import com.example.scoapi.model.entity.StatusSolicitacao;
import com.example.scoapi.model.repository.PerguntaRepository;
import com.example.scoapi.model.repository.RespostaQuestionarioRepository;
import com.example.scoapi.model.repository.SolicitacaoAdocaoRepository;
import com.example.scoapi.model.repository.StatusSolicitacaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SolicitacaoAdocaoService {

    private static final String STATUS_PENDENTE = "Pendente";
    private static final String STATUS_APROVADA = "Aprovada";
    private static final String STATUS_RECUSADA = "Recusada";
    private static final String STATUS_CANCELADA = "Cancelada";

    private final SolicitacaoAdocaoRepository repository;
    private final StatusSolicitacaoRepository statusRepository;
    private final RespostaQuestionarioRepository respostaRepository;
    private final PerguntaRepository perguntaRepository;

    public SolicitacaoAdocaoService(SolicitacaoAdocaoRepository repository,
                                    StatusSolicitacaoRepository statusRepository,
                                    RespostaQuestionarioRepository respostaRepository,
                                    PerguntaRepository perguntaRepository) {
        this.repository = repository;
        this.statusRepository = statusRepository;
        this.respostaRepository = respostaRepository;
        this.perguntaRepository = perguntaRepository;
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

    @Transactional
    public SolicitacaoAdocao enviarSolicitacao(SolicitacaoAdocao solicitacao) {
        solicitacao.setDataSolicitacao(LocalDate.now());
        solicitacao.setStatus(buscarStatus(STATUS_PENDENTE));
        solicitacao.setDataDecisao(null);
        solicitacao.setMotivoRecusa(null);
        return salvar(solicitacao);
    }

    @Transactional
    public SolicitacaoAdocao cancelarSolicitacao(Long id) {
        SolicitacaoAdocao solicitacao = buscar(id);
        solicitacao.setStatus(buscarStatus(STATUS_CANCELADA));
        return repository.save(solicitacao);
    }

    @Transactional
    public SolicitacaoAdocao aprovar(Long id) {
        SolicitacaoAdocao solicitacao = buscar(id);
        solicitacao.setStatus(buscarStatus(STATUS_APROVADA));
        solicitacao.setDataDecisao(LocalDate.now());
        return repository.save(solicitacao);
    }

    @Transactional
    public SolicitacaoAdocao recusar(Long id, String motivo) {
        if (motivo == null || motivo.trim().isEmpty()) {
            throw new RegraNegocioException("Motivo da recusa é obrigatório");
        }
        SolicitacaoAdocao solicitacao = buscar(id);
        solicitacao.setStatus(buscarStatus(STATUS_RECUSADA));
        solicitacao.setDataDecisao(LocalDate.now());
        solicitacao.setMotivoRecusa(motivo);
        return repository.save(solicitacao);
    }

    @Transactional
    public List<RespostaQuestionario> responder(Long solicitacaoId, List<RespostaQuestionario> respostas) {
        SolicitacaoAdocao solicitacao = buscar(solicitacaoId);
        if (respostas == null || respostas.isEmpty()) {
            throw new RegraNegocioException("É necessário informar ao menos uma resposta");
        }
        for (RespostaQuestionario resposta : respostas) {
            if (resposta.getConteudo() == null || resposta.getConteudo().trim().isEmpty()) {
                throw new RegraNegocioException("Conteúdo da resposta é obrigatório");
            }
            if (resposta.getPergunta() == null || resposta.getPergunta().getId() == null) {
                throw new RegraNegocioException("Pergunta da resposta é obrigatória");
            }
            Pergunta pergunta = perguntaRepository.findById(resposta.getPergunta().getId())
                    .orElseThrow(() -> new RegraNegocioException("Pergunta não encontrada"));
            resposta.setPergunta(pergunta);
            resposta.setSolicitacao(solicitacao);
            respostaRepository.save(resposta);
        }
        return respostas;
    }

    private SolicitacaoAdocao buscar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Solicitação de adoção não encontrada"));
    }

    private StatusSolicitacao buscarStatus(String status) {
        return statusRepository.findByStatus(status)
                .orElseThrow(() -> new RegraNegocioException("Status '" + status + "' não cadastrado"));
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
