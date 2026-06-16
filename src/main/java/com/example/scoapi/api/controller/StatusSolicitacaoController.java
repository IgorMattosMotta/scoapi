package com.example.scoapi.api.controller;

import com.example.scoapi.api.dto.StatusSolicitacaoDTO;
import com.example.scoapi.exception.RegraNegocioException;
import com.example.scoapi.model.entity.StatusSolicitacao;
import com.example.scoapi.service.StatusSolicitacaoService;
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
@RequestMapping("api/statusSolicitacao")
@RequiredArgsConstructor
@CrossOrigin
public class StatusSolicitacaoController {
    private final StatusSolicitacaoService service;

    @GetMapping()
    public ResponseEntity get(){
        List<StatusSolicitacao> adotante = service.getStatus();
        return ResponseEntity.ok(adotante.stream().map(StatusSolicitacaoDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity find(@PathVariable("id") Long id){
        Optional<StatusSolicitacao> adotante = service.getStatusById(id);
        if(!adotante.isPresent()){
            return new ResponseEntity("Usuário não encontrado!", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(adotante.map(StatusSolicitacaoDTO::create));
    }
    @PostMapping()
    public ResponseEntity post(@RequestBody StatusSolicitacaoDTO dto) {
        try {
            StatusSolicitacao statusSolicitacao = converter(dto);
            statusSolicitacao = service.salvar(statusSolicitacao);
            return new ResponseEntity(statusSolicitacao, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody StatusSolicitacaoDTO dto) {
        if (!service.getStatusById(id).isPresent()) {
            return new ResponseEntity("Status da Solicitação não encontrado!", HttpStatus.NOT_FOUND);
        }
        try {
            StatusSolicitacao statusSolicitacao = converter(dto);
            statusSolicitacao.setId(id);
            service.salvar(statusSolicitacao);
            return ResponseEntity.ok(statusSolicitacao);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<StatusSolicitacao> statusSolicitacao = service.getStatusById(id);
        if (!statusSolicitacao.isPresent()) {
            return new ResponseEntity("Status da Solicitação não encontrado!", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(statusSolicitacao.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public StatusSolicitacao converter(StatusSolicitacaoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        StatusSolicitacao statusSolicitacao = modelMapper.map(dto, StatusSolicitacao.class);

        return statusSolicitacao;
    }
}
