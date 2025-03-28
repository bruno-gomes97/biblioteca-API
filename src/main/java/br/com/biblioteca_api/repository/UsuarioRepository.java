package br.com.biblioteca_api.repository;

import br.com.biblioteca_api.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, UUID> {

    Optional<UsuarioEntity> findByCpf(String cpf);
    Optional<UsuarioEntity> findByNome(String nome);
}
