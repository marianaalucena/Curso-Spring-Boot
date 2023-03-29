package io.github.marianaalucena.rest.controller;

import io.github.marianaalucena.domain.entity.Cliente;
import io.github.marianaalucena.domain.repository.Clientes;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Controller //recebe requisições http
@RequestMapping("/api/clientes")
public class ClienteController {


    private Clientes clientes;

    public ClienteController(Clientes clientes){
        this.clientes = clientes;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity getClienteById(@PathVariable Integer id){
        Optional<Cliente> cliente = clientes.findById(id);
        if(cliente.isPresent()){
            return ResponseEntity.ok(cliente.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/salvar")
    @ResponseBody
    public ResponseEntity save(@RequestBody Cliente cliente){
       Cliente clienteSalvo = clientes.save(cliente);
       return ResponseEntity.ok(clienteSalvo);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity delete(@PathVariable Integer id){
        Optional<Cliente> cliente = clientes.findById(id);
        if(cliente.isPresent()){
            clientes.delete(cliente.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity update(@PathVariable Integer id, @RequestBody Cliente cliente){

        return clientes.findById(id).map(clienteExistente -> {
            cliente.setId((clienteExistente.getId()));
            clientes.save(cliente);

            return ResponseEntity.noContent().build(); //nao retorna nada
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/find")
    @ResponseBody
    public ResponseEntity find(Cliente filtro){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher
                        (ExampleMatcher.StringMatcher.CONTAINING); //pesquisa a partir de qualquer letra/palavra
        Example example = Example.of(filtro, matcher);
        List<Cliente> listaAll = clientes.findAll(example);
        return ResponseEntity.ok(listaAll);
    }
}
