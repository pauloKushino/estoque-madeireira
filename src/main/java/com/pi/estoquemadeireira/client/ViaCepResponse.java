package com.pi.estoquemadeireira.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// Modelo cru da API externa (viacep.com.br). O campo "erro" vem true
// quando o CEP tem formato valido mas nao existe na base deles.
@JsonIgnoreProperties(ignoreUnknown = true)
public record ViaCepResponse(
        String cep,
        String logradouro,
        String bairro,
        String localidade,
        String uf,
        Boolean erro
) {
}
