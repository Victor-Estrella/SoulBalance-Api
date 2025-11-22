package br.com.fiap.SoulBalance.entity;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_SOULBALANCE_USUARIO")
@SequenceGenerator(name = "usuario", sequenceName = "SQ_TB_SOULBALANCE_USUARIO", allocationSize = 1)

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class UsuarioEntity {
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private java.util.List<AtividadeEntity> atividades;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private java.util.List<CheckinManualEntity> checkinsManuais;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private java.util.List<DadosSensorEntity> dadosSensores;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private java.util.List<RecomendacaoEntity> recomendacoes;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario")
    @SequenceGenerator(name = "usuario", sequenceName = "SQ_TB_SOULBALANCE_USUARIO", allocationSize = 1)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "senha")
    private String senha;

    @CreatedDate
    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

}
