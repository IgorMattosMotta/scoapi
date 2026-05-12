package com.example.scoapi.api.dto;

import com.example.scoapi.model.entity.StatusAnimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class StatusAnimalDTO {
    private Long id;
    private String estado;

    public static StatusAnimalDTO create (StatusAnimal statusAnimal){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(statusAnimal, StatusAnimalDTO.class);
    }
}
