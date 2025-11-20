package br.com.fiap.SoulBalance.dto;

import br.com.fiap.SoulBalance.entity.CheckinManualEntity;
import br.com.fiap.SoulBalance.entity.UsuarioEntity;
import br.com.fiap.SoulBalance.enun.ValorEnun;
import lombok.*;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class CheckinManualResponseDto {

    private Long chekinId;

    private ValorEnun humor;

    private ValorEnun energia;

    private ValorEnun foco;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private LocalDateTime time;

    private Long usuarioId;

    public static CheckinManualResponseDto from(CheckinManualEntity checkinManual) {
        return CheckinManualResponseDto
                .builder()
                .chekinId(checkinManual.getChekinId())
                .humor(checkinManual.getHumor())
                .energia(checkinManual.getEnergia())
                .foco(checkinManual.getFoco())
                .time(checkinManual.getTime())
                .usuarioId(checkinManual.getUsuario() != null ? checkinManual.getUsuario().getId() : null)
                .build();
    }

}
