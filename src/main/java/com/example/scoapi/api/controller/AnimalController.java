package com.example.scoapi.api.controller;

import com.example.scoapi.api.dto.AnimalDTO;
import com.example.scoapi.model.entity.Animal;
import com.example.scoapi.service.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/animal")
@RequiredArgsConstructor
@CrossOrigin
public class AnimalController {
    private final AnimalService service;

    @GetMapping()
    public ResponseEntity get(){
        List<Animal> adotante = service.getAnimais();
        return ResponseEntity.ok(adotante.stream().map(AnimalDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity find(@PathVariable("id") Long id){
        Optional<Animal> adotante = service.getAnimalById(id);
        if(!adotante.isPresent()){
            return new ResponseEntity("Usuário não encontrado!", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(adotante.map(AnimalDTO::create));
    }
}
