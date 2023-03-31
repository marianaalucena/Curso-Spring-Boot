package io.github.marianaalucena.service;

import io.github.marianaalucena.domain.entity.Pedido;
import io.github.marianaalucena.domain.enums.StatusPedido;
import io.github.marianaalucena.rest.dto.PedidoDTO;

import java.util.Optional;

//service carrega as regras de negócio
public interface PedidoService {

    Pedido salvar (PedidoDTO dto);

    Optional<Pedido> obterPedidoCompleto(Integer id);

    void atualizaStatus(Integer id, StatusPedido statusPedido);
}
