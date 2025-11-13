package br.com.fiap.SoulBalance.repository;

import br.com.fiap.SoulBalance.entity.DadosSensorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DadosSensorRepository extends JpaRepository<DadosSensorEntity, Long> {
}
