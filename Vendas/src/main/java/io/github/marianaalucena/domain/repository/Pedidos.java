package io.github.marianaalucena.domain.repository;

import io.github.marianaalucena.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Pedidos extends JpaRepository<Pedido, Integer> {
}
