package com.example.scoapi.api.dto;

import com.example.scoapi.model.entity.Questionario;
import com.example.scoapi.model.entity.RegistroVacina;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;

public class RegistroVacinaDTO {
    private Long id;
    private LocalDate dataAplicacao;
    private Integer dose;
    private String nome;

    public static RegistroVacinaDTO create(RegistroVacina registroVacina) {
        ModelMapper modelMapper = new ModelMapper();
        RegistroVacinaDTO dto = modelMapper.map(registroVacina, RegistroVacinaDTO.class);
        dto.nome = registroVacina.getProtocolo().getNomeVacina();
        return dto;
    }
}
