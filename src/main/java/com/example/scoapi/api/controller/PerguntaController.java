
package com.example.scoapi.api.controller;

import com.example.scoapi.api.dto.PerguntaDTO;
import com.example.scoapi.model.entity.Pergunta;
import com.example.scoapi.service.PerguntaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/pergunta")
@RequiredArgsConstructor
@CrossOrigin
public class PerguntaController {
    private final PerguntaService service;

    @GetMapping()
    public ResponseEntity get(){
        List<Pergunta> adotante = service.getPerguntas();
        return ResponseEntity.ok(adotante.stream().map(PerguntaDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity find(@PathVariable("id") Long id){
        Optional<Pergunta> adotante = service.getPerguntaById(id);
        if(!adotante.isPresent()){
            return new ResponseEntity("Usuário não encontrado!", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(adotante.map(PerguntaDTO::create));
    }
}
