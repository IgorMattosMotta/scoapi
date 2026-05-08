package com.example.scoapi.api.dto;

import com.example.scoapi.model.entity.ONG;
import com.example.scoapi.model.entity.Pergunta;
import com.example.scoapi.model.entity.Questionario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionarioDTO {
    private Long id;
    private Long idOng;
    private String nomeOng;


    public static QuestionarioDTO create(Questionario questionario) {
        ModelMapper modelMapper = new ModelMapper();
        QuestionarioDTO dto = modelMapper.map(questionario, QuestionarioDTO.class);
       if(questionario.getOng() != null){
           dto.idOng = questionario.getOng().getId();
           dto.nomeOng = questionario.getOng().getNome();
       }
        return dto;
    }
}
