package br.com.vendas.domain.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class VendaRequest {

    @NotNull(message = "Obrigatorio Data de Venda")
    private LocalDate dataVenda;
    @NotNull(message = "Obrigatorio forma de pagamento")
    private String formaPagamento;
    @NotNull(message = "Obrigatorio status")
    private String status;
    @NotNull(message = "Obrigatorio nome do cliente")
    private String nomeCliente;
    @NotNull(message = "Obrigatorio valor de venda")
    private Double valorVenda;
    @Size(min = 1, message = "Obritorio envia pelo menos um item")
    @Valid
    private List<VendaItemRequest> itensVenda;
}
