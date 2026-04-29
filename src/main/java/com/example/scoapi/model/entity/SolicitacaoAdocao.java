package com.example.saaapi.model.entity;

import com.example.scoapi.model.entity.Adotante;
import com.example.scoapi.model.entity.Animal;
import com.example.scoapi.model.entity.RespostaQuestionario;
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
public class SolicitacaoAdocao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataSolicitacao;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private com.example.saaapi.model.entity.StatusSolicitacao status;

    private LocalDate dataDecisao;
    private String motivoRecusa;

    @ManyToOne
    @JoinColumn(name = "adotante_id")
    private Adotante adotante;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;

    @OneToMany(mappedBy = "solicitacao", cascade = CascadeType.ALL)
    private List<RespostaQuestionario> respostas;
}