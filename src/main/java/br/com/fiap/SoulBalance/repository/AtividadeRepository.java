package br.com.fiap.SoulBalance.repository;

import br.com.fiap.SoulBalance.entity.AtividadeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AtividadeRepository extends JpaRepository<AtividadeEntity, Long> {

    List<AtividadeEntity> findByUsuarioIdAndInicioBetween(Long usuarioId, LocalDateTime inicio, LocalDateTime fim);

}
