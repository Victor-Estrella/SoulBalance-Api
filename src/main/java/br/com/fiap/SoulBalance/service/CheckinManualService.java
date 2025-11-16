package br.com.fiap.SoulBalance.service;

import br.com.fiap.SoulBalance.dto.CheckinManualRequestDto;
import br.com.fiap.SoulBalance.dto.CheckinManualResponseDto;
import br.com.fiap.SoulBalance.entity.CheckinManualEntity;
import br.com.fiap.SoulBalance.entity.UsuarioEntity;
import br.com.fiap.SoulBalance.exception.NotFoundException;
import br.com.fiap.SoulBalance.repository.CheckinManualRepository;
import br.com.fiap.SoulBalance.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CheckinManualService {

    @Autowired
    private CheckinManualRepository checkinManualRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Salva o humor, energia e foco do usuário logado e dispara a análise da IA.
     * @param dto DTO com os valores de humor, energia e foco.
     * @param userId ID do usuário logado.
     * @return DTO de resposta do Check-in salvo.
     */
    @Transactional
    public CheckinManualResponseDto registrarCheckin(CheckinManualRequestDto dto, Long userId) {
        UsuarioEntity usuario = usuarioRepository.findById(userId)
                .orElseThrow(NotFoundException.forUser(userId));

        CheckinManualEntity checkin = CheckinManualEntity.builder()
                .humor(dto.getHumor())
                .energia(dto.getEnergia())
                .foco(dto.getFoco())
                .time(LocalDateTime.now())
                .usuario(usuario)
                .build();

        CheckinManualEntity savedCheckin = checkinManualRepository.save(checkin);

//        dispararAnaliseIA(savedCheckin.getUsuario().getUserId(), savedCheckin);

        return CheckinManualResponseDto.from(savedCheckin);
    }

    /**
     * Retorna todos os check-ins de um dia específico para um usuário.
     * @param userId ID do usuário.
     * @param data Data de referência.
     * @return Lista de CheckinManualResponseDto.
     */
    public List<CheckinManualResponseDto> buscarHistoricoDiario(Long userId, LocalDate data) {

        LocalDateTime inicioDoDia = data.atStartOfDay();
        LocalDateTime fimDoDia = data.plusDays(1).atStartOfDay().minusNanos(1);

        List<CheckinManualEntity> historico = checkinManualRepository
                .findByUsuarioIdAndTimeBetween(userId, inicioDoDia, fimDoDia);

        return historico.stream()
                .map(CheckinManualResponseDto::from)
                .toList();
    }

//    /**
//     * CRUCIAL: Após salvar o check-in, este método é chamado para notificar
//     * o AnaliseDiariaIAService para que ele comece a processar os dados do dia.
//     * @param userId ID do usuário.
//     * @param checkin Entidade Check-in recém-salva.
//     */
//    public void dispararAnaliseIA(Long userId, CheckinManualEntity checkin) {
//
//        // Chama o método no AnaliseDiariaIAService para iniciar o fluxo da IA
//        analiseDiariaIAService.processarNovoCheckin(userId, checkin);
//
//        System.out.println("Análise IA disparada para o usuário: " + userId + " após Check-in.");
//    }
}