package br.com.fiap.SoulBalance.dto;

import br.com.fiap.SoulBalance.entity.UsuarioEntity;
import br.com.fiap.SoulBalance.enun.TipoAtividade;

import java.time.Instant;

public class TipoAtividadeResponseDto {
    private Long atividadeId;

    private TipoAtividade tipoAtividade;

    private Instant inicio;

    private Instant fim;

    private Long duracaoMinutosAtividade;

    private UsuarioEntity usuario;
}
