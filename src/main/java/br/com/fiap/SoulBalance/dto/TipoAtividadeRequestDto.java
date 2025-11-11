package br.com.fiap.SoulBalance.dto;

import br.com.fiap.SoulBalance.enun.TipoAtividade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

public class TipoAtividadeRequestDto {

    @NotBlank
    private TipoAtividade tipoAtividade;

    @NotNull
    private Instant inicio;

    @NotNull
    private Instant fim; //ver um jeito de contar um relógio

}
