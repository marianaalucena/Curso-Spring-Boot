package io.github.marianaalucena.rest.controller;

import io.github.marianaalucena.exception.PedidoNaoEncontradoException;
import io.github.marianaalucena.exception.RegraNegocioException;
import io.github.marianaalucena.rest.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvices {

    @ExceptionHandler(RegraNegocioException.class) //tratador de exception (erros)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleRegraNegocioException(RegraNegocioException ex){
        String mensagemErro = ex.getMessage();
        return new ApiErrors(mensagemErro);
    }

    @ExceptionHandler(PedidoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handlePedidoNaoEncontradoException(PedidoNaoEncontradoException ex){
        return new ApiErrors(ex.getMessage());
    }

}
