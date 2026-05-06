package com.example.scoapi.api.dto;

import com.example.scoapi.model.entity.ProtocoloVacina;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProtocoloVacinaDTO {

    private Long id;

    private String nomeVacina;
    private Integer quantidadeDoses;
    private Integer intervaloInterdoses;
    private String descricao;

     private Integer dose;

    public static ProtocoloVacinaDTO create(ProtocoloVacina ProtocoloVacina
    ) {
        ModelMapper modelMapper = new ModelMapper();
        ProtocoloVacinaDTO dto = modelMapper.map(ProtocoloVacina
                , ProtocoloVacinaDTO.class);
       // dto.dose = ProtocoloVacina.getRegistros().get;
        return dto;
    }

}
