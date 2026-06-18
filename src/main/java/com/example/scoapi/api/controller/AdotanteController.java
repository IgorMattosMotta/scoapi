package com.example.scoapi.api.controller;

import com.example.scoapi.api.dto.AdotanteDTO;
import com.example.scoapi.exception.RegraNegocioException;
import com.example.scoapi.model.entity.Adotante;
import com.example.scoapi.service.AdotanteService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/adotante")
@RequiredArgsConstructor
@CrossOrigin

public class AdotanteController {
    private final AdotanteService service;

    @GetMapping()
    public ResponseEntity get(){
        List<Adotante> adotante = service.getAdotantes();
        return ResponseEntity.ok(adotante.stream().map(AdotanteDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity find(@PathVariable("id") Long id){
        Optional<Adotante> adotante = service.getAdotanteById(id);
        if(!adotante.isPresent()){
            return new ResponseEntity("Usuário não encontrado!", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(adotante.map(AdotanteDTO::create));
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody AdotanteDTO dto) {
        try {
            Adotante adotante = converter(dto);
            adotante = service.salvar(adotante);
            return new ResponseEntity(adotante, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody AdotanteDTO dto) {
        if (!service.getAdotanteById(id).isPresent()) {
            return new ResponseEntity("Adotante não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Adotante adotante = converter(dto);
            adotante.setId(id);
            service.salvar(adotante);
            return ResponseEntity.ok(adotante);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Adotante> adotante = service.getAdotanteById(id);
        if (!adotante.isPresent()) {
            return new ResponseEntity("Adotante não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(adotante.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Adotante converter(AdotanteDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Adotante adotante = modelMapper.map(dto, Adotante.class);
        return adotante;
    }
}
