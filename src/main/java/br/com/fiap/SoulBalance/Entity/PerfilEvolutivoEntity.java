package br.com.fiap.SoulBalance.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_SOULBALANCE_PERFIL_EVOLUTIVO")
@SequenceGenerator(name = "perfil", sequenceName = "SQ_TB_SOULBALANCE_PERFIL_EVOLUTIVO", allocationSize = 1)

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class PerfilEvolutivoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "perfil_id")
    private Long perfil_id;

    @Column(name = "pto_autocuidado")
    private double ptoAutocuidado;

    @Column(name = "pto_resiliencia")
    private double ptoResiliencia;

    @Column(name = "senha")
    private String senha;

    @Column(name = "data_last_update")
    private LocalDateTime dataLastUpdate;

    @OneToMany
    private UsuarioEntity usuario;

}
