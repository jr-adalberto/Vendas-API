package br.com.vendas.service;

import br.com.vendas.domain.ApiException;
import br.com.vendas.domain.entity.VendaEntity;
import br.com.vendas.domain.entity.VendaItemEntity;
import br.com.vendas.domain.request.UpdateRequest;
import br.com.vendas.domain.request.VendaItemRequest;
import br.com.vendas.domain.request.VendaRequest;
import br.com.vendas.domain.response.VendaResponse;
import br.com.vendas.repository.VendaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;

@Service
public class VendaService {

    @Autowired
    private VendaRepo vendaRepo;

    @Autowired
    private Validator validator;


    public VendaResponse inserirVenda(VendaRequest vendaRequest) {
        VendaEntity vendaEntity = convertToEntity(vendaRequest);
        VendaEntity vendaSalva = vendaRepo.save(vendaEntity);
        return convertToResponse(vendaSalva);
    }

    public VendaResponse atualizarVenda(Long id, UpdateRequest request) {
        var venda = getVendaById(id);
        if (venda == null) {
            VendaEntity venda1 = vendaRepo.findById(id)
                    .orElseThrow(() -> new ApiException("Nenhuma venda cadastrada neste ID", HttpStatus.BAD_REQUEST));
        }
        venda.setDataVenda(request.getDataVenda());
        venda.setFormaPagamento(request.getFormaPagamento());
        venda.setStatus(request.getStatusVenda());
        venda.setNomeCliente(request.getCliente());
        venda.setValorVenda(request.getValor());

        var vendaAtualizada = vendaRepo.save(venda);
        return convertToResponse(vendaAtualizada);
    }


    public VendaResponse deletarVenda(Long id) {
        VendaEntity venda = vendaRepo.findById(id)
                .orElseThrow(() -> new ApiException("Nenhuma venda cadastrada neste ID", HttpStatus.BAD_REQUEST));

        vendaRepo.delete(venda);

        return convertToResponse(venda);
    }


    public List<VendaResponse> findAll() {
        List<VendaEntity> vendas = vendaRepo.findAll();
        vendas.stream()
                .findAny()
                .orElseThrow(() -> new ApiException("Nenhuma venda cadastrada", HttpStatus.BAD_REQUEST));

        return convertToListResponse(vendas);
    }

    public VendaResponse buscarVendaById(Long vendaId) {
        var venda = getVendaById(vendaId);
        return this.convertToResponse(venda);
    }

    private List<VendaResponse> convertToListResponse(List<VendaEntity> listaVenda) {
        if (listaVenda.isEmpty())
            return new ArrayList<>();

        List<VendaResponse> listaResponse = new ArrayList<>();
        for (VendaEntity venda : listaVenda)
            listaResponse.add(convertToResponse(venda));
        return listaResponse;
    }

    public VendaEntity convertToEntity(VendaRequest vendaRequest) {
        VendaEntity vendaEntity = new VendaEntity();
        vendaEntity.setDataVenda(vendaRequest.getDataVenda());
        vendaEntity.setFormaPagamento(vendaRequest.getFormaPagamento());
        vendaEntity.setStatus(vendaRequest.getStatus());
        vendaEntity.setNomeCliente(vendaRequest.getNomeCliente());
        vendaEntity.setValorVenda(vendaRequest.getValorVenda());

        List<VendaItemEntity> listaItemEntity = new ArrayList<>();
        for (VendaItemRequest request : vendaRequest.getItensVenda()) {
            VendaItemEntity vendaItemEntity = new VendaItemEntity();
            vendaItemEntity.setDescricao(request.getDescricao());
            vendaItemEntity.setVlrUnit(request.getVlrUnit());
            vendaItemEntity.setQtd(request.getQtd());
            vendaItemEntity.setVlrTotal(request.getVlrTotal());
            vendaItemEntity.setVlrVenda(request.getVlrVenda());
            vendaItemEntity.setVenda(vendaEntity);

            listaItemEntity.add(vendaItemEntity);
        }
        vendaEntity.setItensVenda(listaItemEntity);
        return vendaEntity;
    }

    private VendaResponse convertToResponse(VendaEntity vendaEntity) {
        return VendaResponse.builder()
                .id(vendaEntity.getId())
                .dataVenda(vendaEntity.getDataVenda())
                .formaPagamento(vendaEntity.getFormaPagamento())
                .statusVenda(vendaEntity.getStatus())
                .cliente(vendaEntity.getNomeCliente())
                .valor(vendaEntity.getValorVenda())
                .itens(vendaEntity.getItensVenda())
                .build();
    }

    private VendaEntity getVendaById(Long id) {
        return vendaRepo.findById(id)
                .orElseThrow(() -> new ApiException("Nenhuma venda cadastrada neste ID", HttpStatus.BAD_REQUEST));
    }
}