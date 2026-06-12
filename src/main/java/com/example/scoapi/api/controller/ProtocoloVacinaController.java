package com.example.scoapi.api.controller;

import com.example.scoapi.api.dto.ProtocoloVacinaDTO;
import com.example.scoapi.exception.RegraNegocioException;
import com.example.scoapi.model.entity.ProtocoloVacina;
import com.example.scoapi.service.ProtocoloVacinaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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


    @PostMapping()
    public ResponseEntity post(@RequestBody ProtocoloVacinaDTO dto) {
        try {
            ProtocoloVacina protocoloVacina = converter(dto);
            protocoloVacina = service.salvar(protocoloVacina);
            return new ResponseEntity(protocoloVacina, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody ProtocoloVacinaDTO dto) {
        if (!service.getProtocoloById(id).isPresent()) {
            return new ResponseEntity("ProtocoloVacina não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            ProtocoloVacina protocoloVacina = converter(dto);
            protocoloVacina.setId(id);
            service.salvar(protocoloVacina);
            return ResponseEntity.ok(protocoloVacina);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<ProtocoloVacina> protocoloVacina = service.getProtocoloById(id);
        if (!protocoloVacina.isPresent()) {
            return new ResponseEntity("ProtocoloVacina não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(protocoloVacina.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public ProtocoloVacina converter(ProtocoloVacinaDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        ProtocoloVacina protocoloVacina = modelMapper.map(dto, ProtocoloVacina.class);
        return protocoloVacina;
    }
}
