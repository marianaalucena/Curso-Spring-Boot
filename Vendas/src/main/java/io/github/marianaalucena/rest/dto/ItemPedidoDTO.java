package io.github.marianaalucena.rest.dto;

import lombok.*;

/*
"items": [
        {
            "produto": 1,
            "quantidade": 10
        }
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedidoDTO {
    private Integer produto;
    private Integer quantidade;


}
