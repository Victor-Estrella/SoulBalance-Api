package br.com.fiap.SoulBalance.repository;

import br.com.fiap.SoulBalance.entity.CheckinManualEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CheckinManualRepository extends JpaRepository<CheckinManualEntity, Long> {

    List<CheckinManualEntity> findByUsuarioIdAndTimeBetween(
            Long usuarioId,
            LocalDateTime startTime,
            LocalDateTime endTime
    );

}
