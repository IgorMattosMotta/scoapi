package com.example.scoapi.api.dto;

import com.example.scoapi.model.entity.Questionario;
import com.example.scoapi.model.entity.RegistroVacina;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistroVacinaDTO {
    private Long id;
    private LocalDate dataAplicacao;
    private Integer dose;
    private String nomeVacina;

    public static RegistroVacinaDTO create(RegistroVacina registroVacina) {
        ModelMapper modelMapper = new ModelMapper();
        RegistroVacinaDTO dto = modelMapper.map(registroVacina, RegistroVacinaDTO.class);
        if(registroVacina.getProtocolo() != null){
            dto.nomeVacina = registroVacina.getProtocolo().getNomeVacina();
        }
        return dto;
    }
}
