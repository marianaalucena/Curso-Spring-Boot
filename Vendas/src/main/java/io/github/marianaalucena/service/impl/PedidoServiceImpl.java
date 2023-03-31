package io.github.marianaalucena.service.impl;

import io.github.marianaalucena.domain.entity.Cliente;
import io.github.marianaalucena.domain.entity.ItemPedido;
import io.github.marianaalucena.domain.entity.Pedido;
import io.github.marianaalucena.domain.entity.Produto;
import io.github.marianaalucena.domain.enums.StatusPedido;
import io.github.marianaalucena.domain.repository.Clientes;
import io.github.marianaalucena.domain.repository.ItemsPedido;
import io.github.marianaalucena.domain.repository.Pedidos;
import io.github.marianaalucena.domain.repository.Produtos;
import io.github.marianaalucena.exception.PedidoNaoEncontradoException;
import io.github.marianaalucena.exception.RegraNegocioException;
import io.github.marianaalucena.rest.dto.ItemPedidoDTO;
import io.github.marianaalucena.rest.dto.PedidoDTO;
import io.github.marianaalucena.service.PedidoService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor //gera construtor com todos os argumentos obrigatorios (com valor final)
public class PedidoServiceImpl implements PedidoService {

    //para que sejam injetados é necessario add ao construtor
    private final Pedidos pedidosRepository;
    private final Clientes clientesReposioty;
    private final Produtos produtosRepository;
    private final ItemsPedido itemsPedidoRepository;

    @Override
    @Transactional
    public Pedido salvar(PedidoDTO dto) {
        Integer idCliente = dto.getCliente();
        Cliente cliente = clientesReposioty.findById(idCliente)
                .orElseThrow(() -> new RegraNegocioException("Código de cliente inválido."));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now()); //data atual
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.REALIZADO);

       List<ItemPedido> itemsPedidos = converterItems(pedido, dto.getItems());
       pedidosRepository.save(pedido);
       itemsPedidoRepository.saveAll(itemsPedidos);
       pedido.setItens(itemsPedidos);
       return pedido;
    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return pedidosRepository.findByIdFetchItens(id);
    }

    @Override
    @Transactional
    public void atualizaStatus(Integer id, StatusPedido statusPedido) {
        pedidosRepository.findById(id)
                .map(pedido -> {
                    pedido.setStatus(statusPedido);
                    return pedidosRepository.save(pedido);
                }).orElseThrow(() -> new PedidoNaoEncontradoException());
    }

    private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> itemsDto){
        if(itemsDto.isEmpty()){
            throw new RegraNegocioException("Não é possível realizar um pedido sem items.");
        }

        return itemsDto.stream()
                .map(dto -> {
                    Integer idProduto = dto.getProduto();
                    Produto produto = produtosRepository.findById(idProduto)
                            .orElseThrow(
                                    () -> new RegraNegocioException("Código de produto inválido: " + idProduto));;

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);

                    return itemPedido;
                }).collect(Collectors.toList());
    }


}
