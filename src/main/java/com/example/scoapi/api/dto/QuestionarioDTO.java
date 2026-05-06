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
    private String ong;


    public static QuestionarioDTO create(Questionario questionario) {
        ModelMapper modelMapper = new ModelMapper();
        QuestionarioDTO dto = modelMapper.map(questionario, QuestionarioDTO.class);
        dto.ong = questionario.getOng().getCnpj();
        return dto;
    }
}
