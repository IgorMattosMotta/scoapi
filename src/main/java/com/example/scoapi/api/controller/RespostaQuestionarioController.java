package com.example.scoapi.api.controller;

import com.example.scoapi.api.dto.RegistroVacinaDTO;
import com.example.scoapi.api.dto.RespostaQuestionarioDTO;
import com.example.scoapi.exception.RegraNegocioException;
import com.example.scoapi.model.entity.RegistroVacina;
import com.example.scoapi.model.entity.RespostaQuestionario;
import com.example.scoapi.service.RespostaQuestionarioService;
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

    @PostMapping()
    public ResponseEntity post(@RequestBody RespostaQuestionarioDTO dto) {
        try {
            RespostaQuestionario respostaQuestionario = converter(dto);
            respostaQuestionario = service.salvar(respostaQuestionario);
            return new ResponseEntity(respostaQuestionario, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody RespostaQuestionarioDTO dto) {
        if (!service.getRespostaById(id).isPresent()) {
            return new ResponseEntity("Resposta do Questionário não encontrada!", HttpStatus.NOT_FOUND);
        }
        try {
            RespostaQuestionario respostaQuestionario = converter(dto);
            respostaQuestionario.setId(id);
            service.salvar(respostaQuestionario);
            return ResponseEntity.ok(respostaQuestionario);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<RespostaQuestionario> respostaQuestionario = service.getRespostaById(id);
        if (!respostaQuestionario.isPresent()) {
            return new ResponseEntity("Resposta do Questionário não encontrada!", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(respostaQuestionario.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    public RespostaQuestionario converter(RespostaQuestionarioDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        RespostaQuestionario respostaQuestionario = modelMapper.map(dto, RespostaQuestionario.class);


        return respostaQuestionario;
    }


}
