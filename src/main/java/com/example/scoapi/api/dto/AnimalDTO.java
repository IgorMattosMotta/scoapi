package com.example.scoapi.api.dto;

import com.example.scoapi.model.entity.ONG;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimalDTO {
    private Long id;

    private String nome;
    private String especie;
    private String raca;
    private LocalDate dataNascimento;
    private String foto;
    private String tamanho;
    private Float peso;
    private String sexo;
    private Boolean castrado;
    private Boolean vermifugado;


    public static AnimalDTO create(ONG ong) {
        ModelMapper modelMapper = new ModelMapper();
        AnimalDTO dto = modelMapper.map(ong, AnimalDTO.class);
        return dto;
    }
}
