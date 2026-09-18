package com.pi.estoquemadeireira.controller;

import com.pi.estoquemadeireira.dto.EnderecoResponseDTO;
import com.pi.estoquemadeireira.service.EnderecoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enderecos")
@Tag(name = "Enderecos", description = "Consulta de endereco por CEP (integracao externa ViaCEP via OpenFeign)")
public class EnderecoController {

    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @GetMapping("/{cep}")
    @Operation(summary = "Consulta endereco pelo CEP usando a API externa ViaCEP")
    @ApiResponse(responseCode = "200", description = "Endereco encontrado")
    @ApiResponse(responseCode = "400", description = "CEP em formato invalido")
    @ApiResponse(responseCode = "404", description = "CEP nao encontrado")
    @ApiResponse(responseCode = "502", description = "Servico externo indisponivel")
    public EnderecoResponseDTO consultar(@PathVariable String cep) {
        return enderecoService.consultarCep(cep);
    }
}
