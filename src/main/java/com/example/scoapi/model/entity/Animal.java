package com.example.scoapi.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String especie;
    private String raca;
    private LocalDate dataNascimento;
    private String foto;
    private String tamanho;
    private Float peso;
    private String sexo;
    private Boolean castrado;
    private Boolean vermifugado;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private StatusAnimal status;

    private String observacoes;

    @ManyToOne
    @JoinColumn(name = "ong_id")
    private ONG ong;

    @OneToMany(mappedBy = "animal")
    private List<com.example.scoapi.model.entity.SolicitacaoAdocao> solicitacoesAdocao;
}