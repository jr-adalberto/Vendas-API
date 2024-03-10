package br.com.vendas.domain.request;

import br.com.vendas.domain.entity.VendaItemEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class UpdateRequest {

    @NotNull(message = "Campo requerido")
    private LocalDate dataVenda;
    @NotBlank(message = "Campo requerido")
    private String formaPagamento;
    @NotBlank(message = "Campo requerido")
    private String statusVenda;
    @NotBlank(message = "Campo requerido")
    private String cliente;
    @NotNull(message = "Campo requerido")
    private Double valor;
    @NotEmpty(message = "Campo requerido")
    private List<VendaItemEntity> itens;
}
