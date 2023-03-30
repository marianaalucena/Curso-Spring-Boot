package io.github.marianaalucena.rest.controller;

import io.github.marianaalucena.domain.repository.Pedidos;
import io.github.marianaalucena.service.PedidoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pedidos")
public class PedidosController {

    private PedidoService service;

    public PedidosController(PedidoService service){
        this.service = service;
    }


}
