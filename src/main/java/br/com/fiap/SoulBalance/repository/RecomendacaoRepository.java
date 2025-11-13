package br.com.fiap.SoulBalance.repository;

import br.com.fiap.SoulBalance.entity.RecomendacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecomendacaoRepository extends JpaRepository<RecomendacaoEntity, Long> {
}
