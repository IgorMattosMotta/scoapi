package com.example.scoapi.api.controller;

import com.example.scoapi.api.dto.QuestionarioDTO;
import com.example.scoapi.exception.RegraNegocioException;
import com.example.scoapi.model.entity.Questionario;
import com.example.scoapi.service.QuestionarioService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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


    @PostMapping()
    public ResponseEntity post(@RequestBody QuestionarioDTO dto) {
        try {
            Questionario questionario = converter(dto);
            questionario = service.salvar(questionario);
            return new ResponseEntity(questionario, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody QuestionarioDTO dto) {
        if (!service.getQuestionarioById(id).isPresent()) {
            return new ResponseEntity("Questionario não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Questionario questionario = converter(dto);
            questionario.setId(id);
            service.salvar(questionario);
            return ResponseEntity.ok(questionario);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Questionario> questionario = service.getQuestionarioById(id);
        if (!questionario.isPresent()) {
            return new ResponseEntity("Questionario não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(questionario.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Questionario converter(QuestionarioDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Questionario questionario = modelMapper.map(dto, Questionario.class);
        return questionario;
    }
}
