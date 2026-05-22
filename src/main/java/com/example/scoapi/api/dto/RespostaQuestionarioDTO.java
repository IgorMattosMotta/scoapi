package com.example.scoapi.api.dto;

import com.example.scoapi.model.entity.RespostaQuestionario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class RespostaQuestionarioDTO {
    private Long id;
    private String conteudo;
    private Long idSolicitacao;
    private Long idPergunta;
    private String textoPergunta;

    public static RespostaQuestionarioDTO create(RespostaQuestionario respostaQuestionario) {
        ModelMapper modelMapper = new ModelMapper();
        RespostaQuestionarioDTO dto = modelMapper.map(respostaQuestionario, RespostaQuestionarioDTO.class);
        if (respostaQuestionario.getSolicitacao() != null) {
            dto.idSolicitacao = respostaQuestionario.getSolicitacao().getId();
        }
        if (respostaQuestionario.getPergunta() != null) {
            dto.idPergunta = respostaQuestionario.getPergunta().getId();
            dto.textoPergunta = respostaQuestionario.getPergunta().getTexto();
        }
        return dto;
    }
}
