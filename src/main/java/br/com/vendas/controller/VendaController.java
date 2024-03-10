package br.com.vendas.controller;

import br.com.vendas.domain.request.UpdateRequest;
import br.com.vendas.domain.request.VendaRequest;
import br.com.vendas.service.VendaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/venda")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarVendaById(@PathVariable(name = "id") Long vendaId) {
        var response = vendaService.buscarVendaById(vendaId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> inserirVenda(@RequestBody @Valid VendaRequest request) {
        var response = vendaService.inserirVenda(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listarVendas() {
        var vendas = vendaService.findAll();
        return new ResponseEntity<>(vendas, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarVenda(
            @PathVariable(name = "id") Long id, @RequestBody @Valid UpdateRequest request) {
        var response = vendaService.atualizarVenda(id, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<?> deletarVenda(@PathVariable(name = "id") Long id) { //nao precisa de espaco no pathvariable
        vendaService.deletarVenda(id);
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
