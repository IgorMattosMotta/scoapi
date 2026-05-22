package com.example.scoapi.api.dto;

import com.example.scoapi.model.entity.SolicitacaoAdocao;
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

    public static SolicitacaoadocaoDTO create(SolicitacaoAdocao solicitacao) {
        ModelMapper modelMapper = new ModelMapper();
        SolicitacaoadocaoDTO dto = modelMapper.map(solicitacao, SolicitacaoadocaoDTO.class);

        if (solicitacao.getAdotante() != null) {
            dto.idAdotante = solicitacao.getAdotante().getId();
            dto.nomeAdotante = solicitacao.getAdotante().getNomeCompleto();
        }
        if (solicitacao.getAnimal() != null) {
            dto.IdAnimal = solicitacao.getAnimal().getId();
            dto.nomeAnimal = solicitacao.getAnimal().getNome();
        }
        if (solicitacao.getStatus() != null) {
            dto.idStatus = solicitacao.getStatus().getId();
            dto.statusDescricao = solicitacao.getStatus().getStatus();
        }
        return dto;
    }


}
