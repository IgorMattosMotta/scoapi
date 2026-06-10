package com.example.scoapi.api.controller;

import com.example.scoapi.api.dto.ONGDTO;
import com.example.scoapi.model.entity.ONG;
import com.example.scoapi.service.ONGService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/ONG")
@RequiredArgsConstructor
@CrossOrigin
public class ONGController {
    private final ONGService service;

    @GetMapping()
    public ResponseEntity get(){
        List<ONG> adotante = service.getOngs();
        return ResponseEntity.ok(adotante.stream().map(ONGDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity find(@PathVariable("id") Long id){
        Optional<ONG> adotante = service.getOngById(id);
        if(!adotante.isPresent()){
            return new ResponseEntity("Usuário não encontrado!", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(adotante.map(ONGDTO::create));
    }
}
