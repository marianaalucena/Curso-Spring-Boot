package io.github.marianaalucena.service.impl;

import io.github.marianaalucena.domain.repository.Pedidos;
import io.github.marianaalucena.service.PedidoService;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {

    private Pedidos pedidosRepository;

    public PedidoServiceImpl(Pedidos pedidosRepository){
        this.pedidosRepository = pedidosRepository;
    }
}
