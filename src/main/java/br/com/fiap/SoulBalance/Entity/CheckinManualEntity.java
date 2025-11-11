package br.com.fiap.SoulBalance.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_SOULBALANCE_CHEKIN_MANUAL")
@SequenceGenerator(name = "chekin", sequenceName = "SQ_TB_SOULBALANCE_CHEKIN_MANUAL", allocationSize = 1)

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class CheckinManualEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chekin_id")
    private Long chekinId;

    @Column(name = "humor")
    private int humor; //criar um enum de 1 a 5

    @Column(name = "energia")
    private int energia; //criar um enum de 1 a 5

    @Column(name = "foco")
    private String foco; //criar um enum de 1 a 5

    @Column(name = "time")
    private LocalDateTime time;

    @ManyToOne
    @JoinColumn(name = "fk_id_usuario")
    private UsuarioEntity usuario;
}
