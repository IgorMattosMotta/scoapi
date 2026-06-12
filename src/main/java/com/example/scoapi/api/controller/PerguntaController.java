
package com.example.scoapi.api.controller;

import com.example.scoapi.api.dto.PerguntaDTO;
import com.example.scoapi.exception.RegraNegocioException;
import com.example.scoapi.model.entity.Pergunta;
import com.example.scoapi.service.PerguntaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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


    @PostMapping()
    public ResponseEntity post(@RequestBody PerguntaDTO dto) {
        try {
            Pergunta pergunta = converter(dto);
            pergunta = service.salvar(pergunta);
            return new ResponseEntity(pergunta, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody PerguntaDTO dto) {
        if (!service.getPerguntaById(id).isPresent()) {
            return new ResponseEntity("Pergunta não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Pergunta pergunta = converter(dto);
            pergunta.setId(id);
            service.salvar(pergunta);
            return ResponseEntity.ok(pergunta);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Pergunta> pergunta = service.getPerguntaById(id);
        if (!pergunta.isPresent()) {
            return new ResponseEntity("Pergunta não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(pergunta.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Pergunta converter(PerguntaDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Pergunta pergunta = modelMapper.map(dto, Pergunta.class);
        return pergunta;
    }
}
