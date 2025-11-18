package br.com.fiap.SoulBalance.repository;

import br.com.fiap.SoulBalance.entity.RecomendacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecomendacaoRepository extends JpaRepository<RecomendacaoEntity, Long> {
}
