package br.com.fiap.SoulBalance.entity;

import br.com.fiap.SoulBalance.enun.ValorEnun;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chekin")
    @SequenceGenerator(name = "chekin", sequenceName = "SQ_TB_SOULBALANCE_CHEKIN_MANUAL", allocationSize = 1)
    @Column(name = "chekin_id")
    private Long chekinId;

    @Column(name = "humor")
    @Enumerated(EnumType.STRING)
    private ValorEnun humor;

    @Column(name = "energia")
    @Enumerated(EnumType.STRING)
    private ValorEnun energia;

    @Column(name = "foco")
    @Enumerated(EnumType.STRING)
    private ValorEnun foco;

    @Column(name = "time")
    private LocalDateTime time;

    @ManyToOne
    @JoinColumn(name = "fk_id_usuario")
    private UsuarioEntity usuario;
}
