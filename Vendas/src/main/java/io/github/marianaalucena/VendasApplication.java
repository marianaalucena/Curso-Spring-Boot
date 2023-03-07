package io.github.marianaalucena;

import io.github.marianaalucena.domain.entity.Cliente;
import io.github.marianaalucena.domain.repositorio.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init(@Autowired Clientes clientes){
        return args -> {
            System.out.println("Salvando clientes");
            clientes.salvar(new Cliente("Mariana"));
            clientes.salvar(new Cliente("Pedro"));

            List<Cliente> all = clientes.obterTodos();
            all.forEach(System.out::println);

            System.out.println("Atualizando");
            all.forEach(c -> {
                c.setNome(c.getNome() + " atualizado.");
                clientes.atualizar(c);
            });

            System.out.println("Buscandor por nome");
            clientes.buscarPorNome("Pe").forEach(System.out::println);

            all = clientes.obterTodos();
            all.forEach(System.out::println);

            System.out.println("Deletando cliente");
          //  clientes.deletar(clientes.buscarPorNome("Pe").get(0).getId());

            all = clientes.obterTodos();
            all.forEach(System.out::println);
        };
    }
    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
