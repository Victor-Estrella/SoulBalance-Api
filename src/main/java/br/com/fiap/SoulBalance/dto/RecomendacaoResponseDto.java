package br.com.fiap.SoulBalance.dto;

import br.com.fiap.SoulBalance.entity.RecomendacaoEntity;
import lombok.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class RecomendacaoResponseDto {
    private Long recomendacaoId;
    private String sugestao;
    private LocalDateTime time;
    private Long usuarioId;

    public static RecomendacaoResponseDto from(RecomendacaoEntity entity) {
        return RecomendacaoResponseDto.builder()
                .recomendacaoId(entity.getRecomendacaoId())
                .sugestao(entity.getSugestao())
                .time(entity.getTime())
                .usuarioId(entity.getUsuario() != null ? entity.getUsuario().getId() : null)
                .build();
    }
}
