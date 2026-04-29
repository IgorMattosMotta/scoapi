package com.example.scoapi.model.entity;

import com.example.scoapi.model.entity.SolicitacaoAdocao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class RespostaQuestionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String conteudo;

    @ManyToOne
    @JoinColumn(name = "solicitacao_id")
    private SolicitacaoAdocao solicitacao;

    @ManyToOne
    @JoinColumn(name = "pergunta_id")
    private com.example.saaapi.model.entity.Pergunta pergunta;
}