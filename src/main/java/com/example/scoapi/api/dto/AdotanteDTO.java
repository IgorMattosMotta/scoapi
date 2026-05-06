package com.example.scoapi.api.dto;

import com.example.scoapi.model.entity.Adotante;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdotanteDTO {

    private Long id;

    private String cpf;
    private String nomeCompleto;
    private LocalDate dataNascimento;
    private String endereco;

    public static AdotanteDTO create(Adotante adotante) {
        ModelMapper modelMapper = new ModelMapper();
        AdotanteDTO dto = modelMapper.map(adotante, AdotanteDTO.class);
        return dto;
    }


}



