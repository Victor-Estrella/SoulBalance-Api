package br.com.fiap.SoulBalance.repository;

import br.com.fiap.SoulBalance.entity.CheckinManualEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CheckinManualRepository extends JpaRepository<CheckinManualEntity, Long> {

    List<CheckinManualEntity> findByUsuarioId(
            Long usuarioId
    );

    @Modifying
    @Transactional
    @Query("DELETE FROM CheckinManualEntity c WHERE c.usuario.id = :userId " +
            "AND (:since IS NULL OR c.time >= :since)")
    int deleteByUsuarioIdAndPeriod(
            @Param("userId") Long userId,
            @Param("since") LocalDateTime since
    );

}
