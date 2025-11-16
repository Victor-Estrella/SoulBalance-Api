package br.com.fiap.SoulBalance.service;

import br.com.fiap.SoulBalance.dto.AtividadeRequestDto;
import br.com.fiap.SoulBalance.dto.AtividadeResponseDto;
import br.com.fiap.SoulBalance.entity.AtividadeEntity;
import br.com.fiap.SoulBalance.entity.UsuarioEntity;
import br.com.fiap.SoulBalance.enun.TipoAtividade;
import br.com.fiap.SoulBalance.exception.NotFoundException;
import br.com.fiap.SoulBalance.repository.AtividadeRepository;
import br.com.fiap.SoulBalance.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AtividadeService {

    @Autowired
    private AtividadeRepository atividadeRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // --- MÉTODOS CRUD ---

    /**
     * Salva uma nova atividade (trabalho, descanso, lazer) para o usuário logado.
     */
    @Transactional
    public AtividadeResponseDto registrarAtividade(AtividadeRequestDto dto, Long userId) {
        UsuarioEntity usuario = usuarioRepository.findById(userId)
                .orElseThrow(NotFoundException.forUser(userId));

        AtividadeEntity atividade = AtividadeEntity.builder()
                .tipoAtividade(dto.getTipoAtividade())
                .descricao(dto.getDescricao())
                .duracaoMinutosAtividade() // vai pegar do método de duração Minuto fim menos inicio
                .horaInicio(LocalDateTime.now()) // Define o início no momento do registro
                .usuario(usuario)
                .build();

        AtividadeEntity savedAtividade = atividadeRepository.save(atividade);
        return AtividadeResponseDto.from(savedAtividade);
    }

    /**
     * Retorna o histórico de atividades (como DTO) dentro de um período.
     * Essencial para construir o dashboard e relatórios.
     */
    public List<AtividadeResponseDto> buscarHistoricoPorPeriodo(Long userId, LocalDateTime inicio, LocalDateTime fim) {
        return atividadeRepository
                .findByUsuarioIdAndHoraInicioBetween(userId, inicio, fim)
                .stream()
                .map(AtividadeResponseDto::from)
                .toList();
    }

    /**
     * Método auxiliar para retornar entidades, usado internamente pelo RecomendacaoIAService.
     */
    public List<AtividadeEntity> buscarHistoricoPorPeriodoEntity(Long userId, LocalDateTime inicio, LocalDateTime fim) {
        return atividadeRepository
                .findByUsuarioIdAndHoraInicioBetween(userId, inicio, fim);
    }

    // --- MÉTODOS DE NEGÓCIO (Cálculos de Carga) ---

    /**
     * Soma a duração de todas as atividades de tipo 'TRABALHO' no período.
     * @param atividades Lista de atividades filtradas por período.
     * @return O total de minutos dedicados ao trabalho.
     */
    public Double calcularCargaTrabalho(List<AtividadeEntity> atividades) {
        return atividades.stream()
                .filter(a -> a.getTipoAtividade() == TipoAtividade.TRABALHO_CRIATIVO)
                .collect(Collectors.summingInt(AtividadeEntity::getDuracaoMinutosAtividade));
    }

    /**
     * Soma a duração de atividades 'DESCANSO' e 'LAZER' no período.
     * @param atividades Lista de atividades filtradas por período.
     * @return O total de minutos dedicados à recuperação.
     */
    public Double calcularTempoRecuperacao(List<AtividadeEntity> atividades) {
        // Filtra por DESCANSO ou LAZER e soma a duração
        return atividades.stream()
                .filter(a -> a.getTipoAtividade() == TipoAtividade.DESCANSO_PASSIVO || a.getTipoAtividade() == TipoAtividade.LAZER_SOCIAL)
                .collect(Collectors.summingInt(AtividadeEntity::getDuracaoMinutosAtividade));
    }
}