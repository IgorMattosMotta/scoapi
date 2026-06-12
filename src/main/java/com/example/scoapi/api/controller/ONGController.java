package com.example.scoapi.api.controller;

import com.example.scoapi.api.dto.ONGDTO;
import com.example.scoapi.exception.RegraNegocioException;
import com.example.scoapi.model.entity.ONG;
import com.example.scoapi.service.ONGService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
        List<ONG> ong = service.getOngs();
        return ResponseEntity.ok(ong.stream().map(ONGDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity find(@PathVariable("id") Long id){
        Optional<ONG> ong = service.getOngById(id);
        if(!ong.isPresent()){
            return new ResponseEntity("Usuário não encontrado!", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(ong.map(ONGDTO::create));
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody ONGDTO dto) {
        try {
            ONG ong = converter(dto);
            ong = service.salvar(ong);
            return new ResponseEntity(ong, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody ONGDTO dto) {
        if (!service.getOngById(id).isPresent()) {
            return new ResponseEntity("ONG não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            ONG ong = converter(dto);
            ong.setId(id);
            service.salvar(ong);
            return ResponseEntity.ok(ong);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<ONG> ong = service.getOngById(id);
        if (!ong.isPresent()) {
            return new ResponseEntity("ONG não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(ong.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public ONG converter(ONGDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        ONG ong = modelMapper.map(dto, ONG.class);
        return ong;
    }
}
