package com.example.scoapi.api.controller;

import com.example.scoapi.api.dto.StatusAnimalDTO;
import com.example.scoapi.model.entity.StatusAnimal;
import com.example.scoapi.service.StatusAnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/statusAnimal")
@RequiredArgsConstructor
@CrossOrigin
public class StatusAnimalController {
    private final StatusAnimalService service;

    @GetMapping()
    public ResponseEntity get(){
        List<StatusAnimal> adotante = service.getStatus();
        return ResponseEntity.ok(adotante.stream().map(StatusAnimalDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity find(@PathVariable("id") Long id){
        Optional<StatusAnimal> adotante = service.getStatusById(id);
        if(!adotante.isPresent()){
            return new ResponseEntity("Usuário não encontrado!", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(adotante.map(StatusAnimalDTO::create));
    }
}
