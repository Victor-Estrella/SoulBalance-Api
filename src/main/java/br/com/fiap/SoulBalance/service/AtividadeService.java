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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AtividadeService {

    @Autowired
    private AtividadeRepository atividadeRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Salva uma nova atividade (trabalho, descanso, lazer) para o usuário logado.
     */
    @Transactional
    @CacheEvict(value = "historicoAtividades", allEntries = true)
    public AtividadeResponseDto saveAtividade(AtividadeRequestDto filter, Long userId) {
        UsuarioEntity usuario = usuarioRepository.findById(userId)
                .orElseThrow(NotFoundException.forUser(userId));

        int duration = filter.getDurationMinutes() != null ? filter.getDurationMinutes() : 0;
        LocalDateTime inicio = LocalDateTime.now();
        LocalDateTime fim = inicio.plusMinutes(duration);

        AtividadeEntity atividade = AtividadeEntity.builder()
            .tipoAtividade(filter.getTipoAtividade() != null ? filter.getTipoAtividade() : TipoAtividade.DESCANSO_PASSIVO)
            .descricao(filter.getDescricao())
            .inicio(inicio)
            .fim(fim)
            .duracaoMinutosAtividade((long) duration)
            .usuario(usuario)
            .build();

        AtividadeEntity savedAtividade = atividadeRepository.save(atividade);
        return AtividadeResponseDto.from(savedAtividade);
    }

    /**
     * Retorna o histórico de atividades (como DTO) dentro de um período.
     * Essencial para construir o dashboard e relatórios.
     */
    @Cacheable(value = "historicoAtividades", key = "{#root.methodName, #inicio, #fim, #userId}")
    public List<AtividadeResponseDto> buscarHistoricoPorPeriodo(LocalDateTime inicio, LocalDateTime fim, Long userId) {
        return atividadeRepository
                .findByUsuarioIdAndInicioBetween(userId, inicio, fim)
                .stream()
                .map(AtividadeResponseDto::from)
                .toList();
    }

    /**
     * Método auxiliar para retornar entidades, usado internamente pelo RecomendacaoIAService.
     */
    @Cacheable(value = "historicoAtividadesEntity", key = "{#root.methodName, #inicio, #fim}")
    public List<AtividadeEntity> buscarHistoricoPorPeriodoEntity(LocalDateTime inicio, LocalDateTime fim) {
        Long userId = getAuthenticatedUserId();
        return atividadeRepository
                .findByUsuarioIdAndInicioBetween(userId, inicio, fim);
    }
    private Long getAuthenticatedUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("Usuário não autenticado");
        }
        Object principal = authentication.getPrincipal();
        if (principal instanceof br.com.fiap.SoulBalance.entity.UsuarioEntity usuario) {
            return usuario.getId();
        }
        if (principal instanceof String str) {
            // Se for email, busca o usuário pelo email
            UsuarioEntity usuario = usuarioRepository.findByEmail(str)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
            return usuario.getId();
        }
        throw new RuntimeException("Não foi possível obter o ID do usuário autenticado");
    }

    public List<AtividadeResponseDto> getAll() {
        return atividadeRepository.findAll()
                .stream()
                .map(AtividadeResponseDto::from)
                .toList();
    }

    public Page<AtividadeResponseDto> findAllPage(PageRequest request) {
        return atividadeRepository.findAll(request)
                .map(AtividadeResponseDto::from);
    }

//    /**
//     * Soma a duração de todas as atividades de tipo 'TRABALHO' no período.
//     */
//    public Double calcularCargaTrabalho(List<AtividadeEntity> atividades) {
//        return atividades.stream()
//                .filter(a -> a.getTipoAtividade() == TipoAtividade.TRABALHO_CRIATIVO)
//                .collect(Collectors.summingInt(AtividadeEntity::getDuracaoMinutosAtividade));
//    }
//
//    /**
//     * Soma a duração de atividades 'DESCANSO' e 'LAZER' no período.
//     */
//    public Double calcularTempoRecuperacao(List<AtividadeEntity> atividades) {
//        // Filtra por DESCANSO ou LAZER e soma a duração
//        return atividades.stream()
//                .filter(a -> a.getTipoAtividade() == TipoAtividade.DESCANSO_PASSIVO || a.getTipoAtividade() == TipoAtividade.LAZER_SOCIAL)
//                .collect(Collectors.summingInt(AtividadeEntity::getDuracaoMinutosAtividade));
//    }

    public long calcularDuracaoMinutos(LocalDateTime inicio, LocalDateTime fim) {
        Duration duracao = Duration.between(inicio, fim);

        return duracao.toMinutes();
    }
}