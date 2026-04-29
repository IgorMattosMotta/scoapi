package com.example.scoapi.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
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

    @Enumerated(EnumType.STRING)
    private StatusAnimal status;

    private String observacoes;

    @ManyToOne
    @JoinColumn(name = "ong_id")
    private ONG ong;

    @OneToMany(mappedBy = "animal")
    private List<SolicitacaoAdocao> solicitacoesAdocao;
}