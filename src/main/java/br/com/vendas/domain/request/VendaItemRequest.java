package br.com.vendas.domain.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VendaItemRequest {

    private String descricao;
    private Double vlrUnit;
    private Double qtd;
    private Double vlrTotal;
    private Double vlrVenda;
}
