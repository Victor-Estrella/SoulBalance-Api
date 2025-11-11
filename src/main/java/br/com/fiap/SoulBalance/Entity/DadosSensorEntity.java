package br.com.fiap.SoulBalance.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_SOULBALANCE_DADOS_SENSOR")
@SequenceGenerator(name = "dados", sequenceName = "SQ_TB_SOULBALANCE_DADOS_SENSOR", allocationSize = 1)

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class DadosSensorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dado_id")
    private Long dado_id;

    @Column(name = "tipo_dado")
    private String tipoDado; //criar um enuns quais são os dados que o usuário pode escolher

    @Column(name = "valor")
    private int valor; //pontuação desses dados

    @Column(name = "time")
    private LocalDateTime time;

    @ManyToOne
    @JoinColumn(name = "fk_id_usuario")
    private UsuarioEntity usuario;
}
