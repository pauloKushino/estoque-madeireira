package com.pi.estoquemadeireira.controller;

import com.pi.estoquemadeireira.dto.EstoqueResponseDTO;
import com.pi.estoquemadeireira.dto.ProdutoRequestDTO;
import com.pi.estoquemadeireira.dto.ProdutoResponseDTO;
import com.pi.estoquemadeireira.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@Tag(name = "Produtos", description = "Cadastro e consulta de produtos da madeireira")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    @Operation(summary = "Lista todos os produtos")
    public List<ProdutoResponseDTO> listar() {
        return produtoService.listar();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um produto pelo id")
    @ApiResponse(responseCode = "404", description = "Produto nao encontrado")
    public ProdutoResponseDTO buscarPorId(@PathVariable Long id) {
        return produtoService.buscarPorId(id);
    }

    @GetMapping("/{id}/estoque")
    @Operation(summary = "Consulta a quantidade disponivel em estoque de um produto")
    @ApiResponse(responseCode = "404", description = "Produto nao encontrado")
    public EstoqueResponseDTO consultarEstoque(@PathVariable Long id) {
        return produtoService.consultarEstoque(id);
    }

    @PostMapping
    @Operation(summary = "Cadastra um novo produto")
    @ApiResponse(responseCode = "201", description = "Produto criado")
    @ApiResponse(responseCode = "400", description = "Dados invalidos")
    public ResponseEntity<ProdutoResponseDTO> criar(@Valid @RequestBody ProdutoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.criar(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um produto existente")
    @ApiResponse(responseCode = "404", description = "Produto nao encontrado")
    public ProdutoResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody ProdutoRequestDTO dto) {
        return produtoService.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclui um produto")
    @ApiResponse(responseCode = "204", description = "Produto excluido")
    @ApiResponse(responseCode = "404", description = "Produto nao encontrado")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        produtoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
