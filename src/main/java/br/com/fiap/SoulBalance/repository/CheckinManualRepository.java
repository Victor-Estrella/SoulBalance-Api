package br.com.fiap.SoulBalance.repository;

import br.com.fiap.SoulBalance.entity.CheckinManualEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckinManualRepository extends JpaRepository<CheckinManualEntity, Long> {
}
