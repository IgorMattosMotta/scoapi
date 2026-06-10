package com.example.scoapi.api.controller;

import com.example.scoapi.api.dto.AdotanteDTO;
import com.example.scoapi.model.entity.Adotante;
import com.example.scoapi.service.AdotanteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
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
}
