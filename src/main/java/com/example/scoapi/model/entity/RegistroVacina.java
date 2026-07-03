package com.example.scoapi.model.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class RegistroVacina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataAplicacao;
    private Integer dose;

    @ManyToOne
    @JoinColumn(name = "protocolo_id")
    private com.example.scoapi.model.entity.ProtocoloVacina protocolo;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;
}