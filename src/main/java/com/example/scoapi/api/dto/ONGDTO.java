package com.example.scoapi.api.dto;

import com.example.scoapi.model.entity.ONG;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ONGDTO {
    private Long id;

    private String login;
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;

    private String cnpj;
    private String nome;


    public static ONGDTO create(ONG ong) {
        ModelMapper modelMapper = new ModelMapper();
        ONGDTO dto = modelMapper.map(ong, ONGDTO.class);
        return dto;
    }

}
