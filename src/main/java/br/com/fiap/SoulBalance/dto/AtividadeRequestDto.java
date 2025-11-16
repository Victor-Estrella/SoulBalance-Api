package br.com.fiap.SoulBalance.dto;

import br.com.fiap.SoulBalance.enun.TipoAtividade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class AtividadeRequestDto {

    @NotBlank
    private TipoAtividade tipoAtividade;

    @NotNull
    private Instant inicio;

    @NotBlank
    private String descricao;

    @NotNull
    private Instant fim; //ver um jeito de contar um relógio

}
