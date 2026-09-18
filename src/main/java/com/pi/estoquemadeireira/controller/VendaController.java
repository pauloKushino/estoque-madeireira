package com.pi.estoquemadeireira.controller;

import com.pi.estoquemadeireira.dto.VendaRequestDTO;
import com.pi.estoquemadeireira.dto.VendaResponseDTO;
import com.pi.estoquemadeireira.service.VendaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vendas")
@Tag(name = "Vendas", description = "Registro e consulta de vendas com baixa de estoque")
public class VendaController {

    private final VendaService vendaService;

    public VendaController(VendaService vendaService) {
        this.vendaService = vendaService;
    }

    @GetMapping
    @Operation(summary = "Lista todas as vendas")
    public List<VendaResponseDTO> listar() {
        return vendaService.listar();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca uma venda pelo id")
    @ApiResponse(responseCode = "404", description = "Venda nao encontrada")
    public VendaResponseDTO buscarPorId(@PathVariable Long id) {
        return vendaService.buscarPorId(id);
    }

    @PostMapping
    @Operation(summary = "Registra uma venda, debitando o estoque dos produtos na mesma transacao")
    @ApiResponse(responseCode = "201", description = "Venda criada com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados invalidos")
    @ApiResponse(responseCode = "404", description = "Produto nao encontrado")
    @ApiResponse(responseCode = "422", description = "Estoque insuficiente para um ou mais itens")
    public ResponseEntity<VendaResponseDTO> criar(@Valid @RequestBody VendaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vendaService.criar(dto));
    }
}
