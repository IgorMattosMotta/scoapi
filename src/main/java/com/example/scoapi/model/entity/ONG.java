package com.example.scoapi.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ONG extends Usuario {

    private String cnpj;
    private String nome;

    @OneToMany(mappedBy = "ong")
    private List<Animal> animais;

    @OneToOne(mappedBy = "ong", cascade = CascadeType.ALL)
    private Questionario questionario;
}