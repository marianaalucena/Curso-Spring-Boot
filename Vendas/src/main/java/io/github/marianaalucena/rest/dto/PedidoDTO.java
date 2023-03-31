package io.github.marianaalucena.rest.dto;

/*
{
    "cliente": 1,
    "total": 100,
    "items": [
        {
            "produto": 1,
            "quantidade": 10
        }
    ]
}
 */

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

//DTO - Data Transfer Object
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {
    private Integer cliente;
    private BigDecimal total;
    private List<ItemPedidoDTO> items;
    private String status;

}

