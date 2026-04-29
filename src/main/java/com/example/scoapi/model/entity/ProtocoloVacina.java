package com.example.saaapi.model.entity;

import com.example.scoapi.model.entity.RegistroVacina;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ProtocoloVacina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeVacina;
    private Integer quantidadeDoses;
    private Integer intervaloInterdoses;
    private String descricao;

    @OneToMany(mappedBy = "protocolo")
    private List<RegistroVacina> registros;
}