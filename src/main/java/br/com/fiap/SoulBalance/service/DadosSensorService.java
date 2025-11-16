package br.com.fiap.SoulBalance.service;

import br.com.fiap.SoulBalance.dto.DadosSensorRequestDto;
import br.com.fiap.SoulBalance.dto.DadosSensorResponseDto;
import br.com.fiap.SoulBalance.entity.DadosSensorEntity;
import br.com.fiap.SoulBalance.entity.UsuarioEntity;
import br.com.fiap.SoulBalance.enun.TipoDadoSensor;
import br.com.fiap.SoulBalance.exception.NotFoundException;
import br.com.fiap.SoulBalance.repository.DadosSensorRepository;
import br.com.fiap.SoulBalance.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DadosSensorService {

    @Autowired
    private DadosSensorRepository dadosSensorRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    /**
     * Salva dados de sono, BPM, passos, etc., enviados pelo cliente.
     * O 'time' é gerado no servidor.
     */
    @Transactional
    public DadosSensorResponseDto registrarDado(DadosSensorRequestDto dto, Long userId) {
        UsuarioEntity usuario = usuarioRepository.findById(userId)
                .orElseThrow(NotFoundException.forUser(userId));

        DadosSensorEntity dado = DadosSensorEntity.builder()
                .tipoDado(dto.getTipoDadoSensor())
                .valor(dto.getValor())
                .time(LocalDateTime.now())
                .usuario(usuario)
                .build();

        DadosSensorEntity savedDado = dadosSensorRepository.save(dado);

        return DadosSensorResponseDto.from(savedDado);
    }

    /**
     * Retorna os dados de um tipo específico (ex: todos os registros de BATIMENTOS_MEDIOS)
     * em um dia para um usuário.
     */
    public List<DadosSensorResponseDto> buscarDadosPorTipo(Long userId, TipoDadoSensor tipo, LocalDate data) {

        LocalDateTime inicioDoDia = data.atStartOfDay();
        LocalDateTime fimDoDia = data.plusDays(1).atStartOfDay().minusNanos(1);

        List<DadosSensorEntity> historico = dadosSensorRepository
                .findByUsuarioIdAndTipoDadoAndTimeBetween(userId, tipo, inicioDoDia, fimDoDia);

        return historico.stream()
                .map(DadosSensorResponseDto::from)
                .toList();
    }

    /**
     * Combina múltiplos registros de sensor (ex: várias leituras de BPM ao longo do dia)
     * para gerar um único valor diário (média/total) para uso na análise da IA.
     * Retorna um mapa de TipoDadoSensor para o valor agregado (ex: Média de BPM ou Total de Passos).
     */
    public Map<TipoDadoSensor, Double> agregarDadosDiarios(Long userId, LocalDate data) {

        LocalDateTime inicioDoDia = data.atStartOfDay();
        LocalDateTime fimDoDia = data.plusDays(1).atStartOfDay().minusNanos(1);

        List<DadosSensorEntity> dadosDoDia = dadosSensorRepository
                .findByUsuarioIdAndTimeBetween(userId, inicioDoDia, fimDoDia);

        if (dadosDoDia.isEmpty()) {
            return Map.of();
        }

        return dadosDoDia.stream()
                .collect(Collectors.groupingBy(
                        DadosSensorEntity::getTipoDado,
                        Collectors.averagingInt(DadosSensorEntity::getValor)
                ));
    }
}