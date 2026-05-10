package com.example.scoapi.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class SolicitacaoadocaoDTO {
    private Long id;
    private String dataSolicitacao;
    private String dataDecisao;
    private String motivoRecusa;

    private Long idAdotante;
    private String nomeAdotante;
    private Long IdAnimal;
    private String nomeAnimal;
    private Long idStatus;
    private String statusDescricao;

    public static SolicitacaoadocaoDTO create( SolicitacaoadocaoDTO solicitacao){
        ModelMapper modelMapper = new ModelMapper();
        SolicitacaoadocaoDTO dto = modelMapper.map(solicitacao, SolicitacaoadocaoDTO.class);

        if(solicitacao.getIdAdotante() != null){
            dto.nomeAdotante = solicitacao.getNomeAdotante();
        }
        return dto;
    }


}
