package br.com.fiap.SoulBalance.dto;

import lombok.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class RecomendacaoRequestDto {
    private String sugestao;
    private LocalDateTime time;
    private Long usuarioId;
}
