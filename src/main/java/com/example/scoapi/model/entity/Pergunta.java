package com.example.scoapi.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String texto;
    private boolean ehAberta;

    @ManyToOne
    @JoinColumn(name = "questionario_id")
    private Questionario questionario;

    @OneToMany(mappedBy = "pergunta")
    private List<RespostaQuestionario> respostas;
}