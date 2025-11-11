package br.com.fiap.SoulBalance.dto;

import br.com.fiap.SoulBalance.entity.UsuarioEntity;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class UsuarioResponseDto {

    private Long userId;
    private String nome;
    private String email;
    private String senha;
    private LocalDateTime dataCriacao;


    public static UsuarioResponseDto from(UsuarioEntity usuario) {
        return UsuarioResponseDto
                .builder()
                .userId(usuario.getUserId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .senha(usuario.getSenha())
                .build();
    }
}
