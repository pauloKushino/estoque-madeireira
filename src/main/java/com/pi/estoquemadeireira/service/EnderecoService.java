package com.pi.estoquemadeireira.service;

import com.pi.estoquemadeireira.client.ViaCepClient;
import com.pi.estoquemadeireira.client.ViaCepResponse;
import com.pi.estoquemadeireira.dto.EnderecoResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class EnderecoService {

    private static final Logger log = LoggerFactory.getLogger(EnderecoService.class);
    private static final Pattern CEP_PATTERN = Pattern.compile("\\d{8}");

    private final ViaCepClient viaCepClient;

    public EnderecoService(ViaCepClient viaCepClient) {
        this.viaCepClient = viaCepClient;
    }

    public EnderecoResponseDTO consultarCep(String cep) {
        // Aceita CEP com ou sem mascara (01001-000 / 01001000)
        String cepLimpo = cep.replaceAll("\\D", "");
        if (!CEP_PATTERN.matcher(cepLimpo).matches()) {
            throw new IllegalArgumentException("CEP invalido: informe 8 digitos numericos");
        }

        ViaCepResponse response = viaCepClient.consultarCep(cepLimpo);

        if (response == null || Boolean.TRUE.equals(response.erro())) {
            log.warn("CEP nao encontrado na base do ViaCEP: {}", cepLimpo);
            throw new EntityNotFoundException("CEP nao encontrado: " + cepLimpo);
        }

        log.info("Endereco consultado via ViaCEP: cep={}, cidade={}/{}", cepLimpo, response.localidade(), response.uf());
        return new EnderecoResponseDTO(
                response.cep(),
                response.logradouro(),
                response.bairro(),
                response.localidade(),
                response.uf()
        );
    }
}
