package com.example.scoapi.api.controller;

import com.example.scoapi.api.dto.StatusAnimalDTO;
import com.example.scoapi.exception.RegraNegocioException;
import com.example.scoapi.model.entity.StatusAnimal;
import com.example.scoapi.service.StatusAnimalService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
    @PostMapping()
    public ResponseEntity post(@RequestBody StatusAnimalDTO dto) {
        try {
            StatusAnimal statusAnimal = converter(dto);
            statusAnimal = service.salvar(statusAnimal);
            return new ResponseEntity(statusAnimal, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody StatusAnimalDTO dto) {
        if (!service.getStatusById(id).isPresent()) {
            return new ResponseEntity("Status do Animal não encontrado!", HttpStatus.NOT_FOUND);
        }
        try {
            StatusAnimal statusAnimal = converter(dto);
            statusAnimal.setId(id);
            service.salvar(statusAnimal);
            return ResponseEntity.ok(statusAnimal);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<StatusAnimal> statusAnimal = service.getStatusById(id);
        if (!statusAnimal.isPresent()) {
            return new ResponseEntity("Status do Animal não encontrado!", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(statusAnimal.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public StatusAnimal converter(StatusAnimalDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        StatusAnimal statusAnimal = modelMapper.map(dto, StatusAnimal.class);

        return statusAnimal;
    }
}
