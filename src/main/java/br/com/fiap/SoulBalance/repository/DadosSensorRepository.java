package br.com.fiap.SoulBalance.repository;

import br.com.fiap.SoulBalance.entity.DadosSensorEntity;
import br.com.fiap.SoulBalance.enun.TipoDadoSensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DadosSensorRepository extends JpaRepository<DadosSensorEntity, Long> {

    /**
     * Corresponde ao método buscarDadosPorTipo(Long userId, TipoDadoSensor tipo, LocalDate data) no Service.
     * Busca todos os registros de sensor de um tipo específico, dentro de um intervalo de tempo, para um usuário.
     *
     * @param usuarioId ID do usuário.
     * @param tipoDado Tipo de dado do sensor (ex: BATIMENTOS_MEDIOS).
     * @param startTime Início do período (ex: 00:00:00 do dia).
     * @param endTime Fim do período (ex: 23:59:59 do dia).
     * @return Lista de DadosSensorEntity.
     */
    List<DadosSensorEntity> findByUsuarioIdAndTipoDadoAndTimeBetween(
            Long usuarioId,
            TipoDadoSensor tipoDado,
            LocalDateTime startTime,
            LocalDateTime endTime
    );

    /**
     * Corresponde ao método agregarDadosDiarios(Long userId, LocalDate data) no Service.
     * Busca todos os registros de sensor (de qualquer tipo) dentro de um intervalo de tempo para um usuário.
     *
     * @param usuarioId ID do usuário.
     * @param startTime Início do período (ex: 00:00:00 do dia).
     * @param endTime Fim do período (ex: 23:59:59 do dia).
     * @return Lista de DadosSensorEntity.
     */
    List<DadosSensorEntity> findByUsuarioIdAndTimeBetween(
            Long usuarioId,
            LocalDateTime startTime,
            LocalDateTime endTime
    );

}
