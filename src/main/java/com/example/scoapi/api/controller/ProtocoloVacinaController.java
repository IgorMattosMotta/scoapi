package com.example.scoapi.api.controller;

import com.example.scoapi.api.dto.ProtocoloVacinaDTO;
import com.example.scoapi.model.entity.ProtocoloVacina;
import com.example.scoapi.service.ProtocoloVacinaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/protocoloVacina")
@RequiredArgsConstructor
@CrossOrigin
public class ProtocoloVacinaController {
    private final ProtocoloVacinaService service;

    @GetMapping()
    public ResponseEntity get(){
        List<ProtocoloVacina> adotante = service.getProtocolos();
        return ResponseEntity.ok(adotante.stream().map(ProtocoloVacinaDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity find(@PathVariable("id") Long id){
        Optional<ProtocoloVacina> adotante = service.getProtocoloById(id);
        if(!adotante.isPresent()){
            return new ResponseEntity("Usuário não encontrado!", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(adotante.map(ProtocoloVacinaDTO::create));
    }
}
