package com.example.scoapi.api.controller;

import com.example.scoapi.api.dto.SolicitacaoAdocaoDTO;
import com.example.scoapi.model.entity.SolicitacaoAdocao;
import com.example.scoapi.service.SolicitacaoAdocaoService;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity get(){
        List<SolicitacaoAdocao> adotante = service.getSolicitacoes();
        return ResponseEntity.ok(adotante.stream().map(SolicitacaoAdocaoDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity find(@PathVariable("id") Long id){
        Optional<SolicitacaoAdocao> adotante = service.getSolicitacaoById(id);
        if(!adotante.isPresent()){
            return new ResponseEntity("Usuário não encontrado!", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(adotante.map(SolicitacaoAdocaoDTO::create));
    }
}
