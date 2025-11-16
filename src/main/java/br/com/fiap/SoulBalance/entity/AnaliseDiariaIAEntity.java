package br.com.fiap.SoulBalance.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_SOULBALANCE_ANALISE_IA")
@SequenceGenerator(name = "analise_ia", sequenceName = "SQ_TB_SOULBALANCE_ANALISE_IA", allocationSize = 1)

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class AnaliseDiariaIAEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "analise_id")
    private Long analiseId;

    @Column(name = "data_referencia", nullable = false, unique = true)
    private LocalDate dataReferencia;

    @Column(name = "resumo_narrativo_ia", columnDefinition = "TEXT")
    private String resumoNarrativoIA;

    @Column(name = "alerta_urgente")
    private Boolean alertaUrgente;

    @Column(name = "fadiga_score_calculado")
    private Double fadigaScoreCalculado;

    @Column(name = "estresse_score_calculado")
    private Double estresseScoreCalculado;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @ManyToOne
    @JoinColumn(name = "fk_id_usuario", nullable = false)
    private UsuarioEntity usuario;
}