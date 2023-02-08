package io.github.marianaalucena.service;

import io.github.marianaalucena.model.Cliente;
import io.github.marianaalucena.repository.ClientesRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientesService {

    private ClientesRespository repository;
    @Autowired //Autowired busca a instância do repository que ja existe no projeto
    public ClientesService(ClientesRespository repository){
        this.repository = repository;
    }

    public void salvarCliente(Cliente cliente){
        validarCliente(cliente);
        //objeto complexo - deve-se usar a injeção de dependencias
        //ClientesRespository clientesRespository = new ClientesRespository();
        this.repository.persistir(cliente);
    }

    public void validarCliente(Cliente cliente){
        //aplicaValidacoes
    }
}
