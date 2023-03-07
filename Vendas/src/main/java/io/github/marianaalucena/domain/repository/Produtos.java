package io.github.marianaalucena.domain.repository;

import io.github.marianaalucena.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Produtos extends JpaRepository<Produto, Integer> {
}
