package br.com.fiap.SoulBalance.dto;

import br.com.fiap.SoulBalance.enun.TipoAtividade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Instant;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class AtividadeRequestDto {

	@NotNull
    private TipoAtividade tipoAtividade;


    // Não enviar datas, backend define início/fim
    private Integer durationMinutes;

    @NotBlank
    private String descricao;



}
