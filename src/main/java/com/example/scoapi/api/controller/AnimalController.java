package com.example.scoapi.api.controller;

import com.example.scoapi.api.dto.AnimalDTO;
import com.example.scoapi.api.dto.RegistroVacinaDTO;
import com.example.scoapi.exception.RegraNegocioException;
import com.example.scoapi.model.entity.Animal;
import com.example.scoapi.service.AnimalService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
        List<Animal> animal = service.getAnimais();
        return ResponseEntity.ok(animal.stream().map(AnimalDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity find(@PathVariable("id") Long id){
        Optional<Animal> animal = service.getAnimalById(id);
        if(!animal.isPresent()){
            return new ResponseEntity("Usuário não encontrado!", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(animal.map(AnimalDTO::create));
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody AnimalDTO dto) {
        try {
            Animal animal = converter(dto);
            animal = service.salvar(animal);
            return new ResponseEntity(animal, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody AnimalDTO dto) {
        if (!service.getAnimalById(id).isPresent()) {
            return new ResponseEntity("Animal não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Animal animal = converter(dto);
            animal.setId(id);
            service.salvar(animal);
            return ResponseEntity.ok(animal);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Animal> animal = service.getAnimalById(id);
        if (!animal.isPresent()) {
            return new ResponseEntity("Animal não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(animal.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}/prontuario")
    public ResponseEntity consultarProntuario(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(service.consultarProntuario(id).stream()
                    .map(RegistroVacinaDTO::create).collect(Collectors.toList()));
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Animal converter(AnimalDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Animal animal = modelMapper.map(dto, Animal.class);
        return animal;
    }
}
