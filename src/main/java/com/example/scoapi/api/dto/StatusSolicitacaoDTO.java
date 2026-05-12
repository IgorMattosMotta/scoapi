package com.example.scoapi.api.dto;

import com.example.scoapi.model.entity.StatusSolicitacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class StatusSolicitacaoDTO {

    private Long id;
    private String status;

    public static StatusSolicitacaoDTO create(StatusSolicitacao statusSolicitacao){

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(statusSolicitacao, StatusSolicitacaoDTO.class);



    }

}
