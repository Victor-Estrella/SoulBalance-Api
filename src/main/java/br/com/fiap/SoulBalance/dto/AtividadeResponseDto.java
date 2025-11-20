package br.com.fiap.SoulBalance.dto;

import br.com.fiap.SoulBalance.entity.AtividadeEntity;
import br.com.fiap.SoulBalance.entity.UsuarioEntity;
import br.com.fiap.SoulBalance.enun.TipoAtividade;
import lombok.*;

import java.time.Instant;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class AtividadeResponseDto {
    private Long atividadeId;

    private TipoAtividade tipoAtividade;


    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private LocalDateTime inicio;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private LocalDateTime fim;

    private Long duracaoMinutosAtividade;

    private Long usuarioId;

    public static AtividadeResponseDto from(AtividadeEntity atividade) {
        return AtividadeResponseDto
                .builder()
                .atividadeId(atividade.getAtividadeId())
                .tipoAtividade(atividade.getTipoAtividade() != null ? atividade.getTipoAtividade() : TipoAtividade.DESCANSO_PASSIVO)
                .inicio(atividade.getInicio())
                .fim(atividade.getFim())
                .duracaoMinutosAtividade(atividade.getDuracaoMinutosAtividade())
                .usuarioId(atividade.getUsuario().getId())
                .build();
    }

}
