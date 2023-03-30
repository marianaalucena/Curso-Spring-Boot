package io.github.marianaalucena.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "produto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id") //nao obrigatorio ja que a entidade e a coluna se chama id
    private Integer id;

    @Column(name = "descricao", length = 100)
    private String descricao;

    @Column(name = "preco_unitario")
    private BigDecimal preco;


}
