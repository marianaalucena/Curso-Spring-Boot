package io.github.marianaalucena.domain.repository;

import io.github.marianaalucena.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItensPedido extends JpaRepository<ItemPedido, Integer> {
}
