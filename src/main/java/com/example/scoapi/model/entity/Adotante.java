package com.example.scoapi.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Adotante extends Usuario {

    private Long id;

    private String cpf;
    private String nomeCompleto;
    private LocalDate dataNascimento;
    private String endereco;

    @OneToMany(mappedBy = "adotante")
    private List<SolicitacaoAdocao> solicitacoes;
}