package br.com.fiap.SoulBalance.dto;

import br.com.fiap.SoulBalance.entity.CheckinManualEntity;
import br.com.fiap.SoulBalance.entity.UsuarioEntity;
import br.com.fiap.SoulBalance.enun.ValorEnun;
import lombok.*;

import java.time.LocalDateTime;

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

    private LocalDateTime time;

    private UsuarioEntity usuario;


    public static CheckinManualResponseDto from(CheckinManualEntity checkinManual) {
        return CheckinManualResponseDto
                .builder()
                .chekinId(checkinManual.getChekinId())
                .humor(checkinManual.getHumor())
                .energia(checkinManual.getEnergia())
                .foco(checkinManual.getFoco())
                .time(checkinManual.getTime())
//                .usuario(checkinManual.getUsuario().getNome())
                .usuario(checkinManual.getUsuario())
                .build();
    }


}
