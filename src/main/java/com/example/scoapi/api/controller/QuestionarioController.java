package com.example.scoapi.api.controller;

import com.example.scoapi.api.dto.QuestionarioDTO;
import com.example.scoapi.model.entity.Questionario;
import com.example.scoapi.service.QuestionarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/questionario")
@RequiredArgsConstructor
@CrossOrigin
public class QuestionarioController {
    private final QuestionarioService service;

    @GetMapping()
    public ResponseEntity get(){
        List<Questionario> adotante = service.getQuestionarios();
        return ResponseEntity.ok(adotante.stream().map(QuestionarioDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity find(@PathVariable("id") Long id){
        Optional<Questionario> adotante = service.getQuestionarioById(id);
        if(!adotante.isPresent()){
            return new ResponseEntity("Usuário não encontrado!", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(adotante.map(QuestionarioDTO::create));
    }
}
