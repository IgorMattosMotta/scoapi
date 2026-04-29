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
public class Questionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "ong_id")
    private ONG ong;

    @OneToMany(mappedBy = "questionario", cascade = CascadeType.ALL)
    private List<com.example.scoapi.model.entity.Pergunta> perguntas;
}