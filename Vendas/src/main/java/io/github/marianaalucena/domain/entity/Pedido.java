package io.github.marianaalucena.domain.entity;

import io.github.marianaalucena.domain.enums.StatusPedido;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pedido")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @ManyToOne //foreign key
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Column(name = "data_pedido")
    private LocalDate dataPedido;

    @Column(name = "total", precision = 20, scale = 2) //10000.00
    private BigDecimal total;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusPedido status;

}
