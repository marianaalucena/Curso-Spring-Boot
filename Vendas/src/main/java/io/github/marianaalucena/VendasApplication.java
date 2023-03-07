package io.github.marianaalucena;

import io.github.marianaalucena.domain.entity.Cliente;
import io.github.marianaalucena.domain.entity.Pedido;
import io.github.marianaalucena.domain.repository.Clientes;
import io.github.marianaalucena.domain.repository.Pedidos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init(@Autowired Clientes clientes,
                                  @Autowired Pedidos pedidos){
        return args -> {
            System.out.println("Salvando clientes");
            Cliente mari = clientes.save(new Cliente("Mariana"));
            Cliente pedro = clientes.save(new Cliente("Pedro"));

            boolean exist = clientes.existsByNome("Mariana");
            System.out.println("Existe um cliente com o nome Mariana? " + exist);

            List<Cliente> result =  clientes.encontrarPorNome("Mariana");
            //result.forEach(System.out::println);

            Pedido p = new Pedido();
            p.setCliente(mari);
            p.setDataPedido(LocalDate.now());
            p.setTotal(BigDecimal.valueOf(100));
            pedidos.save(p);
//
//            Cliente cliente = clientes.findClienteFetchPedidos(mari.getId());
//            System.out.println(cliente);
//            System.out.println(cliente.getPedidos());

            pedidos.findByCliente(mari).forEach(System.out::println);

        };
    }
    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
