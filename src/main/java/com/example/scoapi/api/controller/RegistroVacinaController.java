package com.example.scoapi.api.controller;

import com.example.scoapi.api.dto.RegistroVacinaDTO;
import com.example.scoapi.exception.RegraNegocioException;
import com.example.scoapi.model.entity.RegistroVacina;
import com.example.scoapi.service.RegistroVacinaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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

    @PostMapping()
    public ResponseEntity post(@RequestBody RegistroVacinaDTO dto) {
        try {
            RegistroVacina registroVacina = converter(dto);
            registroVacina = service.salvar(registroVacina);
            return new ResponseEntity(registroVacina, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody RegistroVacinaDTO dto) {
        if (!service.getRegistroById(id).isPresent()) {
            return new ResponseEntity("Registro de Vacina não encontrado!", HttpStatus.NOT_FOUND);
        }
        try {
            RegistroVacina registroVacina = converter(dto);
            registroVacina.setId(id);
            service.salvar(registroVacina);
            return ResponseEntity.ok(registroVacina);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<RegistroVacina> registroVacina = service.getRegistroById(id);
        if (!registroVacina.isPresent()) {
            return new ResponseEntity("Registro de Vacina não encontrado!", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(registroVacina.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

        public RegistroVacina converter(RegistroVacinaDTO dto) {
            ModelMapper modelMapper = new ModelMapper();
            RegistroVacina registroVacina = modelMapper.map(dto, RegistroVacina.class);
            return registroVacina;
        }
}
