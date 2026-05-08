package com.example.scoapi.api.dto;

import com.example.scoapi.model.entity.Animal;
import com.example.scoapi.model.entity.ONG;
import com.example.scoapi.model.entity.RegistroVacina;
import com.example.scoapi.model.entity.RespostaQuestionario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class RespostaQuestionarioDTO {
    private Long id;
    private String conteudo;
    private Long idSolicitacao;
    private Long idPergunta;
    private String textoPergunta;

    public static RespostaQuestionario create(RespostaQuestionario respostaQuestionario) {
        ModelMapper modelMapper = new ModelMapper();
        RespostaQuestionario dto = modelMapper.map(respostaQuestionario, RespostaQuestionario.class);
        if(respostaQuestionario.getSolicitacao() != null){
            dto.idSolicitacao = respostaQuestionario.getSolicitacao().getId();
        }
        if(respostaQuestionario.getPergunta() != null){
            dto.idPergunta = respostaQuestionario.getPergunta().getId();
            dto.textoPergunta = respostaQuestionario.getPergunta().getId();
        }
        return dto;
    }
}
