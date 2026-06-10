package com.example.scoapi.api.controller;

import com.example.scoapi.api.dto.RespostaQuestionarioDTO;
import com.example.scoapi.model.entity.RespostaQuestionario;
import com.example.scoapi.service.RespostaQuestionarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/respostaQuestionario")
@RequiredArgsConstructor
@CrossOrigin
public class RespostaQuestionarioController {
    private final RespostaQuestionarioService service;

    @GetMapping()
    public ResponseEntity get(){
        List<RespostaQuestionario> adotante = service.getRespostas();
        return ResponseEntity.ok(adotante.stream().map(RespostaQuestionarioDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity find(@PathVariable("id") Long id){
        Optional<RespostaQuestionario> adotante = service.getRespostaById(id);
        if(!adotante.isPresent()){
            return new ResponseEntity("Usuário não encontrado!", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(adotante.map(RespostaQuestionarioDTO::create));
    }
}
