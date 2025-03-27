package br.com.biblioteca_api.repository;

import br.com.biblioteca_api.entity.LivroEntity;
import br.com.biblioteca_api.enums.Genero;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface LivroRepository extends JpaRepository<LivroEntity, UUID> {
    List<LivroEntity> findByGenero(Genero genero);

}
