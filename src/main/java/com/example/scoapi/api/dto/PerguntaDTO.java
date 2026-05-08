package com.example.scoapi.api.dto;

import com.example.scoapi.model.entity.Pergunta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerguntaDTO {
    private Long id;
    private String texto;
    private boolean ehAberta;


    private Long idQuestionario;

    public static PerguntaDTO create (Pergunta pergunta) {
        ModelMapper modelMapper = new ModelMapper();
        PerguntaDTO dto = modelMapper.map(pergunta, PerguntaDTO.class);
        if(pergunta.getQuestionario() != null){

            dto.idQuestionario = pergunta.getQuestionario().getId();
        }
        return dto;
    }
}
