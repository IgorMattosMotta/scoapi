package com.example.scoapi.api.controller;

import com.example.scoapi.api.dto.RegistroVacinaDTO;
import com.example.scoapi.model.entity.RegistroVacina;
import com.example.scoapi.service.RegistroVacinaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/registrosVacina")
@RequiredArgsConstructor
@CrossOrigin
public class RegistroVacinaController {
    private final RegistroVacinaService service;

    @GetMapping()
    public ResponseEntity get(){
        List<RegistroVacina> adotante = service.getRegistros();
        return ResponseEntity.ok(adotante.stream().map(RegistroVacinaDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity find(@PathVariable("id") Long id){
        Optional<RegistroVacina> adotante = service.getRegistroById(id);
        if(!adotante.isPresent()){
            return new ResponseEntity("Usuário não encontrado!", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(adotante.map(RegistroVacinaDTO::create));
    }
}
