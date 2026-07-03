package com.example.scoapi.api.controller;

import com.example.scoapi.api.dto.RespostaQuestionarioDTO;
import com.example.scoapi.api.dto.SolicitacaoAdocaoDTO;
import com.example.scoapi.exception.RegraNegocioException;
import com.example.scoapi.model.entity.RespostaQuestionario;
import com.example.scoapi.model.entity.SolicitacaoAdocao;
import com.example.scoapi.service.SolicitacaoAdocaoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/solicitacaoAdocao")
@RequiredArgsConstructor
@CrossOrigin
public class SolicitacaoAdocaoController {
    private final SolicitacaoAdocaoService service;

    @GetMapping()
    public ResponseEntity get() {
        List<SolicitacaoAdocao> adotante = service.getSolicitacoes();
        return ResponseEntity.ok(adotante.stream().map(SolicitacaoAdocaoDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity find(@PathVariable("id") Long id) {
        Optional<SolicitacaoAdocao> adotante = service.getSolicitacaoById(id);
        if (!adotante.isPresent()) {
            return new ResponseEntity("Usuário não encontrado!", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(adotante.map(SolicitacaoAdocaoDTO::create));
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody SolicitacaoAdocaoDTO dto) {
        try {
            SolicitacaoAdocao solicitacaoAdocao = converter(dto);
            solicitacaoAdocao = service.salvar(solicitacaoAdocao);
            return new ResponseEntity(solicitacaoAdocao, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody SolicitacaoAdocaoDTO dto) {
        if (!service.getSolicitacaoById(id).isPresent()) {
            return new ResponseEntity("Solicitação de Adoção não encontrada!", HttpStatus.NOT_FOUND);
        }
        try {
            SolicitacaoAdocao solicitacaoAdocao = converter(dto);
            solicitacaoAdocao.setId(id);
            service.salvar(solicitacaoAdocao);
            return ResponseEntity.ok(solicitacaoAdocao);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<SolicitacaoAdocao> solicitacaoAdocao = service.getSolicitacaoById(id);
        if (!solicitacaoAdocao.isPresent()) {
            return new ResponseEntity("Solicitação de Adoção não encontrada!", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(solicitacaoAdocao.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{id}/enviar")
    public ResponseEntity enviar(@PathVariable("id") Long id) {
        Optional<SolicitacaoAdocao> solicitacao = service.getSolicitacaoById(id);
        if (!solicitacao.isPresent()) {
            return new ResponseEntity("Solicitação de Adoção não encontrada!", HttpStatus.NOT_FOUND);
        }
        try {
            return ResponseEntity.ok(SolicitacaoAdocaoDTO.create(service.enviarSolicitacao(solicitacao.get())));
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity cancelar(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(SolicitacaoAdocaoDTO.create(service.cancelarSolicitacao(id)));
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}/aprovar")
    public ResponseEntity aprovar(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(SolicitacaoAdocaoDTO.create(service.aprovar(id)));
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}/recusar")
    public ResponseEntity recusar(@PathVariable("id") Long id, @RequestParam("motivo") String motivo) {
        try {
            return ResponseEntity.ok(SolicitacaoAdocaoDTO.create(service.recusar(id, motivo)));
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{id}/respostas")
    public ResponseEntity responder(@PathVariable("id") Long id, @RequestBody List<RespostaQuestionario> respostas) {
        try {
            return ResponseEntity.ok(service.responder(id, respostas).stream()
                    .map(RespostaQuestionarioDTO::create).collect(Collectors.toList()));
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public SolicitacaoAdocao converter(SolicitacaoAdocaoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        SolicitacaoAdocao solicitacaoAdocao = modelMapper.map(dto, SolicitacaoAdocao.class);

        return solicitacaoAdocao;
    }
 }