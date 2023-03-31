package io.github.marianaalucena.rest.controller;

import io.github.marianaalucena.domain.entity.ItemPedido;
import io.github.marianaalucena.domain.entity.Pedido;
import io.github.marianaalucena.domain.enums.StatusPedido;
import io.github.marianaalucena.domain.repository.Pedidos;
import io.github.marianaalucena.rest.dto.AtualizacaoStatusPedidoDTO;
import io.github.marianaalucena.rest.dto.InformacoesItemPedidoDTO;
import io.github.marianaalucena.rest.dto.InformacoesPedidoDTO;
import io.github.marianaalucena.rest.dto.PedidoDTO;
import io.github.marianaalucena.service.PedidoService;
import jdk.jshell.Snippet;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pedidos")
public class PedidosController {

    private PedidoService service;

    public PedidosController(PedidoService service){
        this.service = service;
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody PedidoDTO dto){
        Pedido pedidoSalvo = service.salvar(dto);
        return pedidoSalvo.getId();
    }

    @GetMapping("/{id}")
    public InformacoesPedidoDTO getById(@PathVariable Integer id){
        return service.obterPedidoCompleto(id)
                .map(p -> converter(p))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));
    }

    @PatchMapping("/{id}") //para atualizar apenas algumas info
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatus(@PathVariable Integer id, @RequestBody AtualizacaoStatusPedidoDTO dto){
        String novoStatus = dto.getNovoStatus();
        service.atualizaStatus(id, StatusPedido.valueOf(novoStatus));
    }

    private InformacoesPedidoDTO converter(Pedido pedido){
        return InformacoesPedidoDTO.builder()
                .codigo(pedido.getId())
                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(pedido.getCliente().getCpf())
                .nomeCliente(pedido.getCliente().getNome())
                .total(pedido.getTotal())
                .status(pedido.getStatus().name())
                .items(converter(pedido.getItens()))
                .build();
    }

    private List<InformacoesItemPedidoDTO> converter(List<ItemPedido> itens){
        if(CollectionUtils.isEmpty(itens)){
            return Collections.emptyList();
        }
        return itens.stream().map(
                item -> InformacoesItemPedidoDTO
                        .builder()
                        .descricaoProduto(item.getProduto().getDescricao())
                        .precoUnitario(item.getProduto().getPreco())
                        .quantidade(item.getQuantidade())
                        .build()
        ).collect(Collectors.toList());
    }
}
