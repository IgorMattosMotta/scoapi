package com.example.scoapi.api.controller;

import com.example.scoapi.api.dto.StatusSolicitacaoDTO;
import com.example.scoapi.model.entity.StatusSolicitacao;
import com.example.scoapi.service.StatusSolicitacaoService;
import lombok.RequiredArgsConstructor;
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
}
