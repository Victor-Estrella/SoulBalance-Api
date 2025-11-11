package br.com.fiap.SoulBalance.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_SOULBALANCE_METRICA_BEM_ESTAR")
@SequenceGenerator(name = "metricas", sequenceName = "SQ_TB_SOULBALANCE_METRICA_BEM_ESTAR", allocationSize = 1)

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class MetricaBemEstarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "metrica_id")
    private Long metricaId;

    @Column(name = "fadiga_score")
    private double fadigaScore;

    @Column(name = "estresse_score")
    private double estresseScore;

    @Column(name = "recuperacao_score")
    private double recuperacaoScore;

    @Column(name = "dataCriacao")
    private LocalDateTime dataCriacao;

    @ManyToOne
    @JoinColumn(name = "fk_id_usuario")
    private UsuarioEntity usuario;
}
