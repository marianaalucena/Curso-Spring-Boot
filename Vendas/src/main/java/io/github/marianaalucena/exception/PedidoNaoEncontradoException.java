package io.github.marianaalucena.exception;

public class PedidoNaoEncontradoException extends RuntimeException{

    public PedidoNaoEncontradoException() {
        super("Pedido não encontrado.");
    }
}
