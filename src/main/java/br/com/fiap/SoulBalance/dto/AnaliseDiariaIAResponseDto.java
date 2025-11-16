package br.com.fiap.SoulBalance.dto;

import br.com.fiap.SoulBalance.entity.AnaliseDiariaIAEntity;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class AnaliseDiariaIAResponseDto {

    private Long analiseId;
    private LocalDate dataReferencia;
    private String resumoNarrativoIA;
    private Boolean alertaUrgente;
    private Double fadigaScoreCalculado;
    private Double estresseScoreCalculado;
    private LocalDateTime dataCriacao;
    private Long usuarioId;

    public static AnaliseDiariaIAResponseDto from(AnaliseDiariaIAEntity entity) {
        return AnaliseDiariaIAResponseDto.builder()
                .analiseId(entity.getAnaliseId())
                .dataReferencia(entity.getDataReferencia())
                .resumoNarrativoIA(entity.getResumoNarrativoIA())
                .alertaUrgente(entity.getAlertaUrgente())
                .fadigaScoreCalculado(entity.getFadigaScoreCalculado())
                .estresseScoreCalculado(entity.getEstresseScoreCalculado())
                .dataCriacao(entity.getDataCriacao())
                .usuarioId(entity.getUsuario().getUserId())
                .build();
    }
}