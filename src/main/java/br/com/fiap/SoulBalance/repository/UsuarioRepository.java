package br.com.fiap.SoulBalance.repository;

import br.com.fiap.SoulBalance.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
}
