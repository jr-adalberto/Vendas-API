package br.com.vendas.domain.response;

import br.com.vendas.domain.entity.VendaItemEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VendaResponse {

    private Long id;
    private LocalDate dataVenda;
    private String formaPagamento;
    private String statusVenda;
    private String cliente;
    private Double valor;
    private String descricao;
    private List<VendaItemEntity> itens;

}
